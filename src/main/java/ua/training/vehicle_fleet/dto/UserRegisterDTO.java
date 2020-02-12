package ua.training.vehicle_fleet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    private String firstName;
    private String lastName;
    private String originFirstName;
    private String originLastName;
    private String login;
    private String email;
    private String password;
    private String confirmPassword;

}
