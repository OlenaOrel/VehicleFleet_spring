package ua.training.vehicle_fleet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.training.vehicle_fleet.dto.AppointmentDto;
import ua.training.vehicle_fleet.dto.AppointmentDtoConverter;
import ua.training.vehicle_fleet.entity.Appointment;
import ua.training.vehicle_fleet.entity.AppointmentStatus;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceTest {

    private static final long APPOINTMENT_ID = 0L;
    private static final long DRIVER_ID = 0l;


    @InjectMocks
    private AppointmentService instance;

    @Mock
    private Appointment appointment;

    @Mock
    private AppointmentDto appointmentDto;

    @Mock
    private Page<Appointment> page;

    @Mock
    private Pageable pageable;

    @Mock
    private AppointmentRepository repository;

    @Mock
    private AppointmentDtoConverter converter;

    @Test
    public void shouldReturnAppointmentListWhenNotFinishedAppointmentExist() {
        when(repository.findByStatusNot(AppointmentStatus.FINISHED)).thenReturn(Collections.singletonList(appointment));

        List<Appointment> result = instance.getNotFinishedAppointment();

        assertThat(result).isNotEmpty();
        assertThat(result).contains(appointment);
    }

    @Test
    public void shouldReturnAppointmentListWhenNotFinishedAppointmentNotFound() {
        List<Appointment> result = instance.getNotFinishedAppointment();

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldDoFinish() {
        instance.doFinish(APPOINTMENT_ID);

        verify(repository).updateStatusById(AppointmentStatus.FINISHED, APPOINTMENT_ID);
    }

    @Test
    public void shouldSaveAppointment() {
        instance.saveAppointment(appointment);
        verify(appointment).setStatus(AppointmentStatus.NEW);
        verify(appointment).setDate(any(LocalDate.class));
        verify(repository).save(appointment);
    }

    @Test
    public void shouldReturnAppointmentWhenGetAppointmentForDriver() throws EntityNotFoundException {
        when(repository.findByDateAndDriver_id(LocalDate.now(), DRIVER_ID)).thenReturn(Optional.of(appointment));
        when(converter.convertToDto(Optional.of(appointment).get())).thenReturn(appointmentDto);
        AppointmentDto result = instance.getAppointmentForDriver(DRIVER_ID);

        assertThat(result).isEqualTo(appointmentDto);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldReturnEmptyWhenRouteNotFound() throws EntityNotFoundException {
        when(repository.findByDateAndDriver_id(LocalDate.now(), DRIVER_ID)).thenReturn(Optional.empty());
        instance.getAppointmentForDriver(DRIVER_ID);
    }

    @Test
    public void shouldSetStatusConfirm() {
        instance.setStatusConfirmed(APPOINTMENT_ID);

        verify(repository).updateStatusById(AppointmentStatus.CONFIRMED, APPOINTMENT_ID);
    }

}
