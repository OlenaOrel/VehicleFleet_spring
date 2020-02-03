package ua.training.vehicle_fleet.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.training.vehicle_fleet.dto.UserRegisterDTO;
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

    public User saveNewUser( @NonNull UserRegisterDTO userDTO ) throws UserExistException {
        User user = createUserFromUserRegisterDTO( userDTO );
        Optional<User> saveUser = Optional.of(userRepository.save( user ));
        if ( saveUser.isPresent() ) {
            return saveUser.get();
        } else {
            throw new UserExistException( user.getEmail() );
        }
    }

    private User createUserFromUserRegisterDTO( @NonNull UserRegisterDTO userDTO ) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .originFirstName(userDTO.getOriginFirstName())
                .originLastName(userDTO.getOriginLastName())
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .password(
                        encodePassword(userDTO.getPassword()))
                .role(UserRole.ROLE_DRIVER)
                .free(true)
                .build();
    }

    private String encodePassword( @NonNull String password ) {
        return new BCryptPasswordEncoder().encode( password );
    }
}
