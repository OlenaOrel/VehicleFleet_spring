package ua.training.vehicle_fleet.exception;

import lombok.Getter;

@Getter
public class RouteNotFoundException extends Exception {

    public RouteNotFoundException(String message) {
        super(message);
    }
}
