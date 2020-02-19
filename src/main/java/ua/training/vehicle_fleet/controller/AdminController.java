package ua.training.vehicle_fleet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.vehicle_fleet.dto.AppointmentDto;
import ua.training.vehicle_fleet.dto.AppointmentDtoConverter;
import ua.training.vehicle_fleet.entity.AppointmentStatus;
import ua.training.vehicle_fleet.service.AppointmentService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final AppointmentService appointmentService;
    private final AppointmentDtoConverter converter;


    @Autowired
    public AdminController(AppointmentService appointmentService, AppointmentDtoConverter converter) {
        this.appointmentService = appointmentService;
        this.converter = converter;
    }


    @GetMapping
    public String adminView(Model model) {
        List<AppointmentDto> appointmentDtoList = converter
                .covertAllToDto(appointmentService.getNotFinishedAppointment());
        model.addAttribute("appointmentDtoList", appointmentDtoList);
        return "admin";
    }

    @PostMapping
    public String adminMain(@RequestParam(required = false) Integer routeNumber,
                            @RequestParam(required = false) AppointmentStatus status,
                            Model model) {
        log.info("route number: {}, status: {}", routeNumber, status);
        if (routeNumber != null && status != null) {
            appointmentService.doFinish(routeNumber, status);
            List<AppointmentDto> appointmentDtoList = converter
                    .covertAllToDto(appointmentService.getNotFinishedAppointment());
            model.addAttribute("appointmentDtoList", appointmentDtoList);
        }
        return "admin";
    }

}
