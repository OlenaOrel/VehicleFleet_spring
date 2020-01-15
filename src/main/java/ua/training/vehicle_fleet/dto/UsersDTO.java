package ua.training.vehicle_fleet.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersDTO {
    private List<UserDTO> users;

    public List<UserDTO> getUsers() {
        return users;
    }
}
