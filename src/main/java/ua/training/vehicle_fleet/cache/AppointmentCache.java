package ua.training.vehicle_fleet.cache;

import org.springframework.stereotype.Component;
import ua.training.vehicle_fleet.entity.Appointment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AppointmentCache {

    private Map<Long, Appointment> appointmentCache = new ConcurrentHashMap<>();

    public Map<Long, Appointment> getAppointmentCache() {
        return appointmentCache;
    }
}
