package ua.training.vehicle_fleet.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.vehicle_fleet.entity.Bus;
import ua.training.vehicle_fleet.entity.Route;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.exception.DriverNotFoundException;
import ua.training.vehicle_fleet.exception.RouteNotFoundException;
import ua.training.vehicle_fleet.repository.BusRepository;
import ua.training.vehicle_fleet.repository.RouteRepository;
import ua.training.vehicle_fleet.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CreateRouteService {

    @Autowired
    private final RouteRepository routeRepository;
    @Autowired
    private final BusRepository busRepository;
    @Autowired
    private final UserRepository userRepository;

    public CreateRouteService(RouteRepository routeRepository,
                              BusRepository busRepository,
                              UserRepository userRepository) {
        this.routeRepository = routeRepository;
        this.busRepository = busRepository;
        this.userRepository = userRepository;
    }

    public Set<Bus> getAllFreeBuses() {
        return Optional.ofNullable(busRepository.findByFree(Boolean.TRUE)).orElseThrow(RuntimeException::new);
    }

    public List<User> getAllBusDriversByBusId(@NonNull Long id) throws DriverNotFoundException {
        return Optional.ofNullable(userRepository.findByBusSet_Id(id))
                .orElseThrow(() ->
                        new DriverNotFoundException("Free driver not found"));
    }

    public Route createRoute(@NonNull Integer numberOfRoute) throws RouteNotFoundException {
        LocalDate date = LocalDate.now();
        Route route = getRouteByNumberOfRoute(numberOfRoute);
        return Route.builder()
                .arrivalToCityEn(route.getArrivalToCityEn())
                .arrivalToCityUk(route.getArrivalToCityUk())
                .departureFromCityEn(route.getDepartureFromCityEn())
                .departureFromCityUk(route.getDepartureFromCityUk())
                .numberOfRoute(route.getNumberOfRoute())
                .date(date)
                .finished(false)
                .build();
    }

    private Route getRouteByNumberOfRoute(@NonNull Integer numberOfRoute) throws RouteNotFoundException {
        return routeRepository.findByNumberOfRoute(numberOfRoute).orElseThrow(() ->
                new RouteNotFoundException("Route not found"));
    }
}
