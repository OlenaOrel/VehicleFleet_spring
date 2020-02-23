package ua.training.vehicle_fleet.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.vehicle_fleet.dto.UserRegisterDTO;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.entity.UserRole;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.exception.UserExistException;
import ua.training.vehicle_fleet.repository.UserRepository;

import java.time.LocalDate;
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

    @Override
    public User loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User with email: " + email + "has not found"));

    }

    public User getUserById(@NonNull Long id) throws EntityNotFoundException {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Driver with id = " + id + "not found"));
    }

    public User saveNewUser(@NonNull UserRegisterDTO userDTO) throws UserExistException {
        User user = createUserFromUserRegisterDTO(userDTO);
        Optional<User> saveUser = Optional.of(userRepository.save(user));
        return saveUser.orElseThrow(() ->
                new UserExistException(user.getEmail()));
    }

    private User createUserFromUserRegisterDTO(@NonNull UserRegisterDTO userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .originFirstName(userDTO.getOriginFirstName())
                .originLastName(userDTO.getOriginLastName())
                .email(userDTO.getEmail())
                .password(
                        encodePassword(userDTO.getPassword()))
                .role(UserRole.ROLE_DRIVER)
                .build();
    }

    private String encodePassword(@NonNull String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Transactional
    public List<User> getNotAppointDriverForBus() {
        LocalDate date = LocalDate.now();
        return userRepository.findNotAppointedDriverForBus(date);
    }
}
