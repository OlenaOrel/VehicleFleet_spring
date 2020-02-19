package ua.training.vehicle_fleet.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.vehicle_fleet.entity.Appointment;
import ua.training.vehicle_fleet.entity.Bus;
import ua.training.vehicle_fleet.entity.Route;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.service.BusService;
import ua.training.vehicle_fleet.service.RouteService;
import ua.training.vehicle_fleet.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppointmentDtoConverter {

    private final UserService userService;
    private final RouteService routeService;
    private final BusService busService;

    @Autowired
    public AppointmentDtoConverter(UserService userService, RouteService routeService, BusService busService) {
        this.userService = userService;
        this.routeService = routeService;
        this.busService = busService;
    }

    public List<AppointmentDto> covertAllToDto(List<Appointment> appointments) {
        return appointments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto convertToDto(Appointment appointment) {
        AppointmentDto result = new AppointmentDto();
        try {
            User user = getDriver(appointment);
            setUserParameters(result, user);
            Route route = getRoute(appointment);
            setRouteParameters(result, route);
            Bus bus = getBus(appointment);
            setBusParameters(result, bus);
            result.setDate(appointment.getDate());
            result.setStatus(appointment.getStatus());
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    private User getDriver(Appointment appointment) throws EntityNotFoundException {
        return userService
                .getUserById(appointment.getDriver().getId());
    }

    private void setUserParameters(AppointmentDto appointmentDto, User user) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        String fullNameUk = user.getOriginFirstName() + " " + user.getOriginLastName();
        appointmentDto.setDriverFullName(fullName);
        appointmentDto.setDriverFullNameUk(fullNameUk);
    }

    private Route getRoute(Appointment appointment) throws EntityNotFoundException {
        return routeService.getRouteById(appointment.getRoute().getId());
    }

    private void setRouteParameters(AppointmentDto appointmentDto, Route route) {
        appointmentDto.setRouteNumber(route.getNumber());
        appointmentDto.setRouteDeparture(route.getDepartureFromCityEn());
        appointmentDto.setRouteDepartureUk(route.getDepartureFromCityUk());
        appointmentDto.setRouteArrival(route.getArrivalToCityEn());
        appointmentDto.setRouteArrivalUk(route.getArrivalToCityUk());
    }

    private Bus getBus(Appointment appointment) throws EntityNotFoundException {
        return busService.getBusById(appointment.getBus().getId());
    }

    private void setBusParameters(AppointmentDto appointmentDto, Bus bus) {
        appointmentDto.setBusMark(bus.getMark());
        appointmentDto.setBusLicensePlate(bus.getLicensePlate());
    }
}
