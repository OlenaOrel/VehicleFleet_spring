package ua.training.vehicle_fleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.vehicle_fleet.entity.Bus;

import java.util.List;
import java.util.Set;

public interface BusRepository extends JpaRepository<Bus, Long> {

    Bus save(Bus bus);

    List<Bus> findAll();

    Set<Bus> findByFree(Boolean isFree);

}
