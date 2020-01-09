package ua.training.vehicle_fleet.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buses",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"number_plate"})})
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String mark;
    @Column(name = "number_plate", nullable = false)
    String numberPlate;
    @Column(name = "number_of_seats", nullable = false)
    Byte numberOfSeats;
    @ManyToMany(mappedBy = "busList")
    List<Driver> drivers;
    @Column(name = "route_list")
    @OneToMany(mappedBy = "bus")
    private List<Route> routeList;
}
