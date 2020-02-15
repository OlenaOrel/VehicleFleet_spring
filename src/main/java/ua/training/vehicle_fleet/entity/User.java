package ua.training.vehicle_fleet.entity;


import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    @Column(name = "origin_first_name", nullable = false, length = 20)
    private String originFirstName;
    @Column(name = "origin_last_name", nullable = false, length = 20)
    private String originLastName;

    @Column(nullable = false, unique = true, length = 65)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(
            name = "bus_driver",
            joinColumns = {@JoinColumn(name = "driver_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "bus_id", referencedColumnName = "id")}
    )
    private List<Bus> busList;


    @Override
    public Set<UserRole> getAuthorities() {
        return new HashSet<>(Collections.singleton(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
