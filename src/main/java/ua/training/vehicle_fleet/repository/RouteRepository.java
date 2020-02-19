package ua.training.vehicle_fleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.vehicle_fleet.entity.Route;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Route save(Route route);

    List<Route> findAll();

    Optional<Route> findById(Long id);

    Optional<Route> findByNumber(Integer numberOfRoute);
}
