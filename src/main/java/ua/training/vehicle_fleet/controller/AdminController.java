package ua.training.vehicle_fleet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.vehicle_fleet.dto.AppointmentDto;
import ua.training.vehicle_fleet.dto.AppointmentDtoConverter;
import ua.training.vehicle_fleet.entity.Appointment;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.service.AppointmentService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    public static final String ADMIN = "admin";
    public static final String APPOINT_HISTORY = "appoint/history";

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
        return ADMIN;
    }

    @PostMapping
    public String adminMain(@RequestParam(required = false) Long appointmentId,
                            Model model) {
        if (appointmentId != null) {
            log.info("Finish appointment id = {}", appointmentId);
            try {
                appointmentService.doFinish(appointmentId);
            } catch (EntityNotFoundException e) {
                log.warn(e.getMessage());
            }
            List<AppointmentDto> appointmentDtoList = converter
                    .covertAllToDto(appointmentService.getNotFinishedAppointment());
            model.addAttribute("appointmentDtoList", appointmentDtoList);
        }
        return ADMIN;
    }

    @GetMapping("/history")
    public String appointmentHistory(@PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC, size = 5) Pageable pageable,
                                     Model model) {
        Page<Appointment> page = appointmentService.getAllForPage(pageable);
        model.addAttribute("page", page);
        return APPOINT_HISTORY;
    }

}
