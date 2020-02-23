package ua.training.vehicle_fleet.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.vehicle_fleet.dto.AppointmentDto;
import ua.training.vehicle_fleet.dto.AppointmentDtoConverter;
import ua.training.vehicle_fleet.entity.Appointment;
import ua.training.vehicle_fleet.entity.AppointmentStatus;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentDtoConverter converter;

    @Autowired
    public AppointmentService(AppointmentRepository repository, AppointmentDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<Appointment> getNotFinishedAppointment() {
        return repository.findByStatusNot(AppointmentStatus.FINISHED);
    }

    @Transactional
    public void doFinish(@NonNull Long appointmentId) {
        repository.updateStatusById(AppointmentStatus.FINISHED, appointmentId);
    }

    public void saveAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.NEW);
        appointment.setDate(LocalDate.now());
        repository.save(appointment);
    }

    public AppointmentDto getAppointmentForDriver(@NonNull Long driverId) throws EntityNotFoundException {
        LocalDate date = LocalDate.now();
        return converter.convertToDto(
                repository.findByDateAndDriver_id(date, driverId).orElseThrow(() ->
                        new EntityNotFoundException("Appointment for driver id = " + driverId + "not found")
                )
        );
    }

    @Transactional
    public void setStatusConfirmed(@NonNull Long appointmentId) {
        repository.updateStatusById(AppointmentStatus.CONFIRMED, appointmentId);
    }

    public Page<Appointment> getAllForPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
