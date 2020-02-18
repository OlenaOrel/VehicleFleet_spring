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
@Table(name = "appointment", uniqueConstraints = {@UniqueConstraint(name = "appointment", columnNames = {"driver_id", "date"})})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", route_id=" + route.getId() +
                ", bus_id=" + bus.getId() +
                ", driver_id=" + driver.getId() +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
