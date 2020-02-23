package ua.training.vehicle_fleet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ua.training.vehicle_fleet.entity.Appointment;
import ua.training.vehicle_fleet.entity.AppointmentStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends Repository<Appointment, Long> {

    void save(Appointment appointment);

    Page<Appointment> findAll(Pageable pageable);

    List<Appointment> findByStatusNot(AppointmentStatus status);

    @Modifying
    @Query("update Appointment t set t.status = :status where t.id = :id")
    void updateStatusById(@Param("status") AppointmentStatus status, @Param("id") Long id);

    Optional<Appointment> findByDateAndDriver_id(LocalDate date, Long id);

}
