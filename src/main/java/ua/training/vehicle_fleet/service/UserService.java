package ua.training.vehicle_fleet.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.training.vehicle_fleet.dto.UserDTO;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        //TODO checking for an empty user list
        return userRepository.findAll();
    }

    public Optional<User> findByUserEmail(UserDTO userDTO) {
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isPresent()) {
            User u = user.get();
            if (u.getPassword().equals(userDTO.getPassword())) {
                return user;
            }
        }
        return Optional.empty();
    }

    @Override
    public User loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User with email: " + email + "has not found"));

    }

}
