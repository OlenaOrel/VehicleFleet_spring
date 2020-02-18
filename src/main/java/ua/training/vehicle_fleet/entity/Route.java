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
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @Column(name = "departure_from_city", nullable = false, length = 50)
    private String departureFromCityEn;

    @Column(name = "arrival_to_city", nullable = false, length = 50)
    private String arrivalToCityEn;

    @Column(name = "departure_from_city_uk", nullable = false, length = 50)
    private String departureFromCityUk;

    @Column(name = "arrival_to_city_uk", nullable = false, length = 50)
    private String arrivalToCityUk;

    @OneToMany
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private List<Appointment> appointments;
}
