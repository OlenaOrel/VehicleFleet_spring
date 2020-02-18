package ua.training.vehicle_fleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.training.vehicle_fleet.entity.Bus;

import java.time.LocalDate;
import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {

    Bus save(Bus bus);

    List<Bus> findAll();

    @Modifying
    @Query("select b from Bus b" +
            " where b.id not in " +
            "(select a.bus from Appointment a " +
            "where a.date = :date)")
    List<Bus> findNotAppointBuses(@Param("date") LocalDate date);
}
