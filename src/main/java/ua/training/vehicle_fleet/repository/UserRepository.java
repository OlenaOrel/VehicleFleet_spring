package ua.training.vehicle_fleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.vehicle_fleet.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail( String email );

    User save( User user );

    List<User> findByBusSet_Id(Long id);
}
