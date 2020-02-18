package ua.training.vehicle_fleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.training.vehicle_fleet.entity.Route;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Route save(Route route);

    List<Route> findAll();

    Optional<Route> findById(Long id);

    Optional<Route> findByNumber(Integer numberOfRoute);

    @Modifying
    @Query("select r from Route r " +
            "where r.id not in (" +
            "select a.driver from Appointment a" +
            " where a.date = :date)")
    List<Route> findNotAppointRoutes(@Param("date") LocalDate date);
}
