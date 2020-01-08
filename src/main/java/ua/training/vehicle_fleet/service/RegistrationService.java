package ua.training.vehicle_fleet.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.entity.UserRole;
import ua.training.vehicle_fleet.exception.UserExistException;
import ua.training.vehicle_fleet.repository.UserRepository;

import java.util.Optional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isPasswordEqualsConfirmPassword(@NonNull User user) {
        return user.getPassword().equals(user.getConfirmPassword());
    }

    public User saveNewUser(@NonNull User user) throws UserExistException {
        Optional<User> saveUser = Optional.of(userRepository.save(user));
        if (saveUser.isPresent()) {
            return saveUser.get();
        } else {
            throw new UserExistException(user.getEmail());
        }
    }

    public void setDefaultUserEmptyValues(@NonNull User user) {
        user.setRole(UserRole.ROLE_USER);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
    }

    public void encodePassword(@NonNull User user) {
        String password = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(password));
    }
}
