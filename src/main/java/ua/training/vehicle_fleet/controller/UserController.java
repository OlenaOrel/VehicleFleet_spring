package ua.training.vehicle_fleet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.vehicle_fleet.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String userForm(Model model, User user) {
        model.addAttribute("user", user);
        return "user";
    }

}
