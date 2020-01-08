package ua.training.vehicle_fleet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.exception.UserExistException;
import ua.training.vehicle_fleet.service.RegistrationService;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {
    private final RegistrationService regService;

    @Autowired
    public RegisterController(RegistrationService regService) {
        this.regService = regService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String registrationFormController(User user) {
        log.info("{}", user);
        if (regService.isPasswordEqualsConfirmPassword(user)) {
            regService.setDefaultUserEmptyValues(user);
            regService.encodePassword(user);
            try {
                regService.saveNewUser(user);
            } catch (UserExistException e) {
                e.printMessage(user.getEmail());
                return "reg_form";
            }
            return "login.html";
        }
        return "reg_form.html";
    }

    @GetMapping
    public String registrationFormView() {
        return "reg_form";
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
