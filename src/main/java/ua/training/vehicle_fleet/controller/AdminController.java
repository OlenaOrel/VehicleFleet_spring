package ua.training.vehicle_fleet.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.vehicle_fleet.entity.User;

@Controller
@RequestMapping( value = "/admin" )
public class AdminController {

    @GetMapping
    public String adminView( @RequestParam( value = "error", required = false ) String error,
                            @RequestParam( value = "logout", required = false ) String logout,
                            Authentication authentication,
                            Model model ) {
        User user = ( User ) authentication.getPrincipal();
        model.addAttribute( "user", user );
        model.addAttribute( "error", error != null );
        model.addAttribute( "logout", logout != null );
        return "admin";
    }
}
