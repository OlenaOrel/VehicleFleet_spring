package ua.training.vehicle_fleet.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.vehicle_fleet.entity.Appointment;
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
        String fullName = appointment.getDriver().getFirstName() + " " + appointment.getDriver().getLastName();
        String fullNameUk = appointment.getDriver().getOriginFirstName() + " " + appointment.getDriver().getOriginLastName();
        return AppointmentDto.builder()
                .id(appointment.getId())
                .routeNumber(appointment.getRoute().getNumber())
                .routeDeparture(appointment.getRoute().getDepartureFromCityEn())
                .routeDepartureUk(appointment.getRoute().getDepartureFromCityUk())
                .routeArrival(appointment.getRoute().getArrivalToCityEn())
                .routeArrivalUk(appointment.getRoute().getArrivalToCityUk())
                .busLicensePlate(appointment.getBus().getLicensePlate())
                .busMark(appointment.getBus().getMark())
                .driverFullName(fullName)
                .driverFullNameUk(fullNameUk)
                .date(appointment.getDate())
                .status(appointment.getStatus())
                .build();
    }
}
