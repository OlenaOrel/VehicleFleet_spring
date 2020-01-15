package ua.training.vehicle_fleet.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserLoginDTO {
    private String email;
    private String password;
}
