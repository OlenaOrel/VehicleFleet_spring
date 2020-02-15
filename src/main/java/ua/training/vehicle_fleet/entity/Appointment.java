package ua.training.vehicle_fleet.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment", uniqueConstraints = {@UniqueConstraint(columnNames = {"driver_id", "date"})})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;
    @ManyToOne
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    private Bus bus;
    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private User driver;
    @Column(name = "date", columnDefinition = "DATE")
    LocalDate date;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    AppointmentStatus status;

}
