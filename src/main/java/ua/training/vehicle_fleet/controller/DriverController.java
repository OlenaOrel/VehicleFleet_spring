package ua.training.vehicle_fleet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/driver")
public class DriverController {

    @RequestMapping(method = RequestMethod.GET)
    public String driverView() {
        return "driver.html";
    }
}
