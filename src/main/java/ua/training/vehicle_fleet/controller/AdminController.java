package ua.training.vehicle_fleet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public void driverView(ModelAndView model) {
        model.setViewName("admin.html");
    }
}
