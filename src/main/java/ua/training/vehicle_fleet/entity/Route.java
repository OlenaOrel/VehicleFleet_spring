package ua.training.vehicle_fleet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Integer numberOfRoute;

    @Column(name = "departure_from_city", nullable = false)
    private String departureFromCity;

    @Column(name = "arival_to_city", nullable = false)
    private String arrivalToCity;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Boolean finished;

    @ManyToMany(mappedBy = "routeList")
    private List<User> drivers;

    @ManyToMany
    @JoinTable(
            name = "buses_on_routs",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "bus_id")
    )
    private List<Bus> busList;

}
