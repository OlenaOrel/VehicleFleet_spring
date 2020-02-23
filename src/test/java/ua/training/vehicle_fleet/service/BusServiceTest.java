package ua.training.vehicle_fleet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.vehicle_fleet.entity.Bus;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.repository.BusRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceTest {

    private static final long BUS_ID = 0L;
    @InjectMocks
    private BusService instance;

    @Mock
    private Bus bus;

    @Mock
    private BusRepository repository;


    @Test
    public void shouldReturnBusWhenBusFound() throws EntityNotFoundException {
        when(repository.findById(BUS_ID)).thenReturn(Optional.of(bus));

        Bus result = instance.getBusById(BUS_ID);

        assertThat(result).isEqualTo(bus);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldReturnEntityNotFoundExceptionWhenRouteNotFound() throws EntityNotFoundException {
        when(repository.findById(BUS_ID)).thenReturn(Optional.empty());
        Bus result = instance.getBusById(BUS_ID);

    }

    @Test
    public void shouldReturnRouteListWhenNotAppointRouteFound() {
        when(repository.findNotAppointBuses(any(LocalDate.class))).thenReturn(Collections.singletonList(bus));

        List<Bus> result = instance.getNotAppointedBuses();

        assertThat(result).isNotEmpty();
        assertThat(result).contains(bus);
    }

    @Test
    public void shouldReturnEmptyListWhenAllRoutesAppointed() {
        List<Bus> result = instance.getNotAppointedBuses();

        assertThat(result).isEmpty();
    }
}
