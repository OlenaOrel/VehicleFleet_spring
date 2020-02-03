package ua.training.vehicle_fleet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(nullable = false)
    private Boolean free;

    @ManyToMany(mappedBy = "busSet")
    private Set<User> drivers;

    @ManyToMany(mappedBy = "busSet")
    private Set<Route> routeSet;
}
