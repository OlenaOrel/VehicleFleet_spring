package ua.training.vehicle_fleet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.service.RegistrationService;

@Slf4j
@Controller
public class RegisterController {
    private final RegistrationService regService;

    @Autowired
    public RegisterController(RegistrationService regService) {
        this.regService = regService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registrationFormController(User user) {
        log.info("{}", user);
    }

    @RequestMapping("/register")
    public String registrationFormView() {
        return "reg_form.html";
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
