package ua.training.vehicle_fleet.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.vehicle_fleet.entity.Route;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.repository.RouteRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public Route getRouteById(@NonNull Long id) throws EntityNotFoundException {
        return routeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Route with id = " + id + "not found")
        );
    }

    @Transactional
    public List<Route> getNotAppointRoute() {
        LocalDate date = LocalDate.now();
        return routeRepository.findNotAppointRoutes(date);
    }
}
