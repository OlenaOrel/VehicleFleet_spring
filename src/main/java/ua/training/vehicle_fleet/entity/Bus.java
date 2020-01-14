package ua.training.vehicle_fleet.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buses",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"number_plate"})})
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String mark;
    @Column(name = "number_plate", nullable = false)
    private String licensePlate;

    @ManyToMany(mappedBy = "busList")
    private List<User> drivers;

    @ManyToMany(mappedBy = "busList")
    private List<Route> routeList;
}
