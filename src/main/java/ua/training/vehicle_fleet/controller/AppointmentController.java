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
import ua.training.vehicle_fleet.entity.Appointment;
import ua.training.vehicle_fleet.entity.Bus;
import ua.training.vehicle_fleet.entity.Route;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.service.AppointmentService;
import ua.training.vehicle_fleet.service.BusService;
import ua.training.vehicle_fleet.service.RouteService;
import ua.training.vehicle_fleet.service.UserService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/appoint")
public class AppointmentController {

    public static final String REDIRECT_ADMIN_APPOINT_BUS = "redirect:/admin/appoint/bus";
    public static final String APPOINT_ADD_BUS = "appoint/add_bus";
    public static final String APPOINT_ADD_ROUTE = "appoint/add_route";
    public static final String REDIRECT_ADMIN_APPOINT_DRIVER = "redirect:/admin/appoint/driver";
    public static final String APPOINT_ADD_DRIVER = "appoint/add_driver";
    public static final String REDIRECT_ADMIN_APPOINT_CONFIRM = "redirect:/admin/appoint/confirm";
    public static final String APPOINT_CONFIRM = "appoint/confirm";
    public static final String REDIRECT_ADMIN = "redirect:/admin";
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
        return APPOINT_ADD_ROUTE;
    }


    @PostMapping(value = "/route")
    public String addRoute(@RequestParam() Long routeId, @AuthenticationPrincipal User user) {
        Appointment appointment = new Appointment();
        log.info("Route id: {}", routeId);
        try {
            appointment.setRoute(routeService.getRouteById(routeId));
            appointmentCache.getAppointmentCache().putIfAbsent(user.getId(), appointment);
            log.info("AppointmentCache route = {}", appointmentCache.getAppointmentCache().get(user.getId()).getRoute());
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return REDIRECT_ADMIN_APPOINT_BUS;
    }

    @GetMapping(value = "/bus")
    public String addBusView(Model model) {
        List<Bus> busList = busService.getNotAppointedBuses();
        model.addAttribute("busList", busList);
        log.info("size of route list: {}", busList.size());
        return APPOINT_ADD_BUS;
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
            return REDIRECT_ADMIN_APPOINT_DRIVER;
        }
        return APPOINT_ADD_BUS;
    }

    @GetMapping(value = "/driver")
    public String addDriverView(Model model) {
        List<User> driverList = userService.getNotAppointDriverForBus();
        model.addAttribute("driverList", driverList);
        return APPOINT_ADD_DRIVER;
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
            return REDIRECT_ADMIN_APPOINT_CONFIRM;
        }
        return APPOINT_ADD_DRIVER;
    }

    @GetMapping(value = "/confirm")
    public String confirmAppointmentView(Model model, @AuthenticationPrincipal User user) {
        Appointment appointment = appointmentCache.getAppointmentCache().get(user.getId());
        log.info("Appointment is ready to confirm: {}", appointment);
        AppointmentDto appointmentDTO = converter.convertToDto(appointment);
        model.addAttribute("appointmentDTO", appointmentDTO);
        return APPOINT_CONFIRM;
    }


    @PostMapping(value = "/confirm")
    public String confirmAppointment(@RequestParam(required = false) boolean confirm, @AuthenticationPrincipal User user) {
        if (confirm) {
            Appointment appointment = appointmentCache.getAppointmentCache().get(user.getId());
            appointmentService.saveAppointment(appointment);
            appointmentCache.getAppointmentCache().remove(user.getId());
            return REDIRECT_ADMIN;
        }
        return APPOINT_CONFIRM;
    }
}
