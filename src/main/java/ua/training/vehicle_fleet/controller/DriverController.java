package ua.training.vehicle_fleet.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.vehicle_fleet.entity.User;

@Controller
@RequestMapping(value = "/driver")
public class DriverController {

    @GetMapping
    public String driverView(Authentication authentication,
                             Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "driver";
    }
}
