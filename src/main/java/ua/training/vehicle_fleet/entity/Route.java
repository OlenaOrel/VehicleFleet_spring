package ua.training.vehicle_fleet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
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
    private String departureFromCityEn;

    @Column(name = "arival_to_city", nullable = false)
    private String arrivalToCityEn;

    @Column(name = "departure_from_city_uk", nullable = false)
    private String departureFromCityUk;

    @Column(name = "arival_to_city_uk", nullable = false)
    private String arrivalToCityUk;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Boolean finished;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RouteStatus status;

    @ManyToMany(mappedBy = "routeSet")
    private Set<User> drivers;

    @ManyToMany
    @JoinTable(
            name = "buses_on_routs",
            joinColumns = {@JoinColumn(name = "route_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "bus_id", referencedColumnName = "id")}
    )
    private Set<Bus> busSet;

}
