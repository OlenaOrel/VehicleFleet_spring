package ua.training.vehicle_fleet.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.vehicle_fleet.entity.Appointment;
import ua.training.vehicle_fleet.entity.AppointmentStatus;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.repository.AppointmentRepository;

import java.util.List;

@Slf4j
@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    @Autowired
    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> getNotFinishedAppointment() {
        return repository.findByStatusNot(AppointmentStatus.FINISHED);
    }

    @Transactional
    public void doFinish(@NonNull Integer routeNumber,
                         @NonNull AppointmentStatus status) throws EntityNotFoundException {
        repository.updateStatusById(AppointmentStatus.FINISHED,
                getByStatusAndRouteNumber(routeNumber, status).getId());

    }

    private Appointment getByStatusAndRouteNumber(Integer routeNumber,
                                                  AppointmentStatus status) throws EntityNotFoundException {
        return repository.findByStatusAndRoute_Number(status, routeNumber).orElseThrow(() ->
                new EntityNotFoundException("Appointment with route number = " + routeNumber +
                        "and status = " + status + "not found"));
    }

    public void saveAppointment(Appointment appointment) {
        repository.save(appointment);
    }

    public Appointment getAppointmentForDriver(@NonNull Long driverId) throws EntityNotFoundException {
        return repository.findByStatusAndDriver_id(AppointmentStatus.NEW, driverId).orElseThrow(() ->
                new EntityNotFoundException("Appointment for driver id = " + driverId + "not found"));
    }

    @Transactional
    public void setStatusConfirmed(@NonNull Long appointmentId) {
        repository.updateStatusById(AppointmentStatus.CONFIRMED, appointmentId);
    }

    public Page<Appointment> getAllForPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
