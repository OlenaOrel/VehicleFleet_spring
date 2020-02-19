package ua.training.vehicle_fleet.dto;

import lombok.*;
import ua.training.vehicle_fleet.entity.AppointmentStatus;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private int routeNumber;
    private String routeDeparture;
    private String routeDepartureUk;
    private String routeArrival;
    private String routeArrivalUk;
    private String busLicensePlate;
    private String busMark;
    private String driverFullName;
    private String driverFullNameUk;
    private LocalDate date;
    private AppointmentStatus status;
}
