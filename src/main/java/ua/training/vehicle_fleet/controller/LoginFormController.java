package ua.training.vehicle_fleet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.training.vehicle_fleet.dto.UsersDTO;
import ua.training.vehicle_fleet.entity.UserRole;
import ua.training.vehicle_fleet.service.UserService;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class LoginFormController {

    private final UserService userService;

    @Autowired
    public LoginFormController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginFormController(Authentication authentication) {
        if (authentication.getAuthorities().contains(UserRole.ROLE_ADMIN)) {
            return "admin";
        }
        if (authentication.getAuthorities().contains(UserRole.ROLE_DRIVER)) {
            return "driver";
        }
        if (authentication.getAuthorities().contains(UserRole.ROLE_USER)) {
            return "user";
        }
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public UsersDTO getAllUser() {
        log.info("{}", userService.getAllUsers());
        return UsersDTO.builder().users(userService.getAllUsers()).build();
    }
}
