package ua.training.vehicle_fleet.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bus",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"license_plate"})})
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String mark;
    @Column(name = "license_plate", unique = true, nullable = false, length = 20)
    private String licensePlate;
    @OneToMany
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    private List<Appointment> appointments;
    @ManyToMany(mappedBy = "busList")
    private List<User> drivers;
}
