package ua.training.vehicle_fleet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.vehicle_fleet.entity.Route;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.repository.RouteRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceTest {

    private static long ROUTE_ID = 0L;

    @InjectMocks
    private RouteService instance;

    @Mock
    private RouteRepository repository;

    @Mock
    private Route route;

    @Test
    public void shouldReturnRouteWhenRouteFound() throws EntityNotFoundException {
        when(repository.findById(ROUTE_ID)).thenReturn(Optional.of(route));

        Route result = instance.getRouteById(ROUTE_ID);

        assertThat(result).isEqualTo(route);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldReturnEmptyWhenRouteNotFound() throws EntityNotFoundException {
        Route result = instance.getRouteById(ROUTE_ID);
    }

    @Test
    public void shouldReturnRouteListWhenNotAppointRouteFound() {
        when(repository.findNotAppointRoutes(any(LocalDate.class))).thenReturn(Collections.singletonList(route));

        List<Route> result = instance.getNotAppointRoute();

        assertThat(result).isNotEmpty();
        assertThat(result).contains(route);
    }

    @Test
    public void shouldReturnEmptyListWhenAllRoutesAppointed() {
        List<Route> result = instance.getNotAppointRoute();

        assertThat(result).isEmpty();
    }
}

