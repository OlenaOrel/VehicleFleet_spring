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
import ua.training.vehicle_fleet.cache.AppointmentCache;
import ua.training.vehicle_fleet.dto.AppointmentDto;
import ua.training.vehicle_fleet.dto.AppointmentDtoConverter;
import ua.training.vehicle_fleet.entity.*;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.service.AppointmentService;
import ua.training.vehicle_fleet.service.BusService;
import ua.training.vehicle_fleet.service.RouteService;
import ua.training.vehicle_fleet.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/appoint")
public class AppointmentController {

    private final RouteService routeService;
    private final BusService busService;
    private final UserService userService;
    private final AppointmentCache appointmentCache;
    private final AppointmentDtoConverter converter;
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(RouteService routeService, BusService busService, UserService userService, AppointmentCache appointmentCache, AppointmentDtoConverter converter, AppointmentService appointmentService) {
        this.routeService = routeService;
        this.busService = busService;
        this.userService = userService;
        this.appointmentCache = appointmentCache;
        this.converter = converter;
        this.appointmentService = appointmentService;
    }

    @GetMapping(value = "/route")
    public String addRoutView(Model model) {
        List<Route> routeList = routeService.getNotAppointRoute();
        model.addAttribute("routeList", routeList);
        log.info("size of route list: {}", routeList.size());
        return "appoint/add_route";
    }


    @PostMapping(value = "/route")
    public String addRoute(@RequestParam() Long routeId, @AuthenticationPrincipal User user) {
        Appointment appointment = new Appointment();
        log.info("Route id: {}", routeId);
        try {
            Route route = routeService.getRouteById(routeId);
            log.info("Route: {}", route);
            appointment.setRoute(route);
            appointmentCache.getAppointmentCache().putIfAbsent(user.getId(), appointment);
            log.info("AppointmentCache route = {}", appointmentCache.getAppointmentCache().get(user.getId()).getRoute());
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/appoint/bus";
    }

    @GetMapping(value = "/bus")
    public String addBusView(Model model) {
        List<Bus> busList = busService.getNotAppointedBuses();
        model.addAttribute("busList", busList);
        log.info("size of route list: {}", busList.size());
        return "appoint/add_bus";
    }

    @PostMapping(value = "/bus")
    public String addBus(@RequestParam(required = false) Long busId, @AuthenticationPrincipal User user) {
        log.info("Bus id = {}", busId);
        if (busId != null) {
            Appointment appointment = appointmentCache.getAppointmentCache()
                    .get(user.getId());
            log.info("Appointment before add bus: {}", appointment.getRoute());
            try {
                appointment.setBus(busService.getBusById(busId));
                appointmentCache.getAppointmentCache().replace(user.getId(), appointment);
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }
            return "redirect:/admin/appoint/driver";
        }
        return "appoint/add_bus";
    }

    @GetMapping(value = "/driver")
    public String addDriverView(Model model) {
        List<User> driverList = userService.getNotAppointDriverForBus();
        model.addAttribute("driverList", driverList);
        return "appoint/add_driver";
    }


    @PostMapping(value = "/driver")
    public String addDriver(@RequestParam(required = false) Long driverId, @AuthenticationPrincipal User user) {
        log.info("Driver id = {}", driverId);
        if (driverId != null) {
            Appointment appointment = appointmentCache.getAppointmentCache().get(user.getId());
            try {
                appointment.setDriver(userService.getUserById(driverId));
                appointmentCache.getAppointmentCache().replace(user.getId(), appointment);
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }
            return "redirect:/admin/appoint/confirm";
        }
        return "appoint/add_driver";
    }

    @GetMapping(value = "/confirm")
    public String confirmAppointmentView(Model model, @AuthenticationPrincipal User user) {
        Appointment appointment = appointmentCache.getAppointmentCache().get(user.getId());
        log.info("Appointment is ready to confirm: {}", appointment);
        AppointmentDto appointmentDTO = converter.convertToDto(appointment);
        model.addAttribute("appointmentDTO", appointmentDTO);
        return "appoint/confirm";
    }


    @PostMapping(value = "/confirm")
    public String confirmAppointment(@RequestParam(required = false) boolean confirm, @AuthenticationPrincipal User user) {
        if (confirm) {
            Appointment appointment = appointmentCache.getAppointmentCache().get(user.getId());
            appointment.setStatus(AppointmentStatus.NEW);
            appointment.setDate(LocalDate.now());
            appointmentService.saveAppointment(appointment);
            appointmentCache.getAppointmentCache().remove(user.getId());
            return "redirect:/admin";
        }
        return "appoint/confirm";
    }
}
