package ua.training.vehicle_fleet.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ua.training.vehicle_fleet.entity.Appointment;
import ua.training.vehicle_fleet.entity.AppointmentStatus;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends Repository<Appointment, Long> {

    void save(Appointment appointment);

    List<Appointment> findByStatusNot(AppointmentStatus status);

    Optional<Appointment> findByStatusAndRoute_Number(AppointmentStatus status, Integer routeNumber);

    @Modifying
    @Query("update Appointment t set t.status = :status where t.id = :id")
    void updateStatusById(@Param("status") AppointmentStatus status, @Param("id") Long id);

}
