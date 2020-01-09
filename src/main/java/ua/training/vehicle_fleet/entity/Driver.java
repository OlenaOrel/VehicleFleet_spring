package ua.training.vehicle_fleet.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "bus_list")
    @ManyToMany
    @JoinTable(
            name = "drivers",
            joinColumns = @JoinColumn(name = "students_id"),
            inverseJoinColumns = @JoinColumn(name = "buses_id"))
    private List<Bus> busList;

    @Column(name = "completed_route")
    @OneToMany(mappedBy = "driver")
    private List<Route> completedRoutes;
}
