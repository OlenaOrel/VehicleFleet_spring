package ua.training.vehicle_fleet.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Bus bus;
    @Column(nullable = false)
    private LocalDate date;
    @Column(name = "departure_from_city", nullable = false)
    private String departureFromCity;
    @Column(name = "arival_to_city", nullable = false)
    private String arrivalToCity;
    private Boolean finished;
}
