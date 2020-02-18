package ua.training.vehicle_fleet.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.vehicle_fleet.entity.Appointment;
import ua.training.vehicle_fleet.entity.AppointmentStatus;
import ua.training.vehicle_fleet.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

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
    public void doFinish(Integer routeNumber, AppointmentStatus status) {
        Optional<Appointment> appointment = repository.findByStatusAndRoute_Number(status, routeNumber);
        if (appointment.isPresent()) {
            repository.updateStatusById(AppointmentStatus.FINISHED,
                    appointment.get().getId());
        }
    }

    public void saveAppointment(Appointment appointment) {
        repository.save(appointment);
    }

}
