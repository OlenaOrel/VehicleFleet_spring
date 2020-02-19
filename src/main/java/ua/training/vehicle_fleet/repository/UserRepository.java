package ua.training.vehicle_fleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.training.vehicle_fleet.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User save(User user);

    @Modifying
    @Query("select u from User u " +
            "where u.id not in " +
            "(select a.driver from Appointment a " +
            "where a.date = :date)")
    List<User> findNotAppointedDriverForBus(@Param("date") LocalDate date);
}
