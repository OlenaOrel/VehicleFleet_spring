package ua.training.vehicle_fleet.exception;

import lombok.Getter;

@Getter
public class DriverNotFoundException extends Exception {

    public DriverNotFoundException(String message) {
        super(message);
    }
}
