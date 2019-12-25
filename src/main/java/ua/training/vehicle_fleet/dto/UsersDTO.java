package ua.training.vehicle_fleet.dto;

import lombok.*;
import ua.training.vehicle_fleet.entity.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersDTO {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }
}
