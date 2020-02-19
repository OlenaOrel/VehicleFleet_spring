package ua.training.vehicle_fleet.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.vehicle_fleet.entity.Bus;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.repository.BusRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus getBusById(@NonNull Long id) throws EntityNotFoundException {
        return busRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Bus with id = " + id + "not found")
        );
    }

    public List<Bus> getNotAppointedBuses() {
        LocalDate date = LocalDate.now();
        return busRepository.findNotAppointBuses(date);
    }
}
