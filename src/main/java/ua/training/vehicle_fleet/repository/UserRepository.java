package ua.training.vehicle_fleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User save(User user);

    Optional<User> findUserByEmailAndPassword(String email, String password);

    List<User> findUsersByRole(UserRole role);
}
