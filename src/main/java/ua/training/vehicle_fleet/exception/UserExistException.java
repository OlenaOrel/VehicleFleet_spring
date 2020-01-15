package ua.training.vehicle_fleet.exception;

public class UserExistException extends Exception {
    private static final String MESSAGE = "User with email: ";
    private static final String EXIST = " exists";
    private String email;

    public UserExistException(String email) {
        this.email = email;
    }

    public void printMessage() {
        System.err.println(new StringBuffer(MESSAGE).append(email).append(EXIST));
    }
}
