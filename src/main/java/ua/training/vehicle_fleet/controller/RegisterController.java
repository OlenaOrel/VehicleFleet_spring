package ua.training.vehicle_fleet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.vehicle_fleet.dto.UserRegisterDTO;
import ua.training.vehicle_fleet.exception.UserExistException;
import ua.training.vehicle_fleet.service.UserService;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final String PASS_NOT_CONFIRM = "redirect:/register?pass_error=true";
    private static final String USER_EXISTS_ERROR = "redirect:/register?error=true";
    private static final String LOGIN_PAGE = "redirect:/login";
    public static final String INPUT_TRUE = "redirect:/register?invalid_input=true";
    public static final String REGISTRATION_VIEW = "reg_form";


    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String registrationFormController(UserRegisterDTO user) {
        log.info("{}", user);
        if (isPassNotConfirm(user)) {
            return PASS_NOT_CONFIRM;
        }
        if (isInputValid(user)) {
            try {
                userService.saveNewUser(user);
            } catch (UserExistException e) {
                e.printMessage();
                return USER_EXISTS_ERROR;
            }
            return LOGIN_PAGE;
        }
        return INPUT_TRUE;
    }

    @GetMapping
    public String registrationFormView() {
        return REGISTRATION_VIEW;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private boolean isInputValid(UserRegisterDTO user) {
        if (user.getFirstName() == null || user.getLastName() == null
                || user.getOriginFirstName() == null || user.getOriginLastName() == null
                || user.getEmail() == null || user.getPassword() == null
                || user.getConfirmPassword() == null) {

            return false;
        }

        return user.getFirstName().matches(RegexConstants.NAME_EN)
                && user.getLastName().matches(RegexConstants.NAME_EN)
                && user.getOriginFirstName().matches(RegexConstants.NAME_UK)
                && user.getOriginLastName().matches(RegexConstants.NAME_UK)
                && user.getEmail().matches(RegexConstants.EMAIL);
    }

    private boolean isPassNotConfirm(UserRegisterDTO user) {
        return !user.getPassword().equals(user.getConfirmPassword());
    }
}
