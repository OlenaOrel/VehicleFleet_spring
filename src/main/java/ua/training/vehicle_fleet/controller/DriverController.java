package ua.training.vehicle_fleet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.vehicle_fleet.dto.AppointmentDto;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.service.AppointmentService;

@Slf4j
@Controller
@RequestMapping(value = "/driver")
public class DriverController {

    public static final String DRIVER = "driver";
    public static final String REDIRECT_DRIVER = "redirect:/driver";

    private final AppointmentService appointmentService;

    @Autowired
    public DriverController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public String driverView(@AuthenticationPrincipal User user, Model model) {
        try {
            AppointmentDto appointmentDto = appointmentService.getAppointmentForDriver(user.getId());
            model.addAttribute("appointmentDto", appointmentDto);
            model.addAttribute("appointmentNotPresent", false);
            log.info("Driver appointment: {}", appointmentDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("appointmentNotPresent", true);
            log.warn(e.getMessage());
        }
        return DRIVER;
    }

    @PostMapping
    public String confirmAppointment(@RequestParam(required = false) Long appointmentId) {
        if (appointmentId != null) {
            appointmentService.setStatusConfirmed(appointmentId);
        }
        return REDIRECT_DRIVER;
    }
}
