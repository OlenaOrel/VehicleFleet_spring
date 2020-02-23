package ua.training.vehicle_fleet.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.training.vehicle_fleet.dto.UserRegisterDTO;
import ua.training.vehicle_fleet.entity.User;
import ua.training.vehicle_fleet.exception.EntityNotFoundException;
import ua.training.vehicle_fleet.exception.UserExistException;
import ua.training.vehicle_fleet.repository.UserRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static final String USER_EMAIL = "user email";
    private static final long USER_ID = 0L;
    private static final String USER_FIRST_NAME = "first name";
    private static final String USER_LAST_NAME = "last name";
    private static final String USER_ORIGIN_FIRS_NAME = "origin first name";
    private static final String USER_ORIGIN_LAST_NAME = "origin last name";
    private static final String USER_PASS = "user pass";


    @InjectMocks
    private UserService instance;

    @Mock
    private UserRepository repository;

    @Mock
    private User user;

    @Mock
    private UserRegisterDTO userRegisterDto;

    @Mock
    private User.UserBuilder userBuilder;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldReturnUserWhenUserExist() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));

        User result = instance.loadUserByUsername(USER_EMAIL);

        assertThat(result).isEqualTo(user);
    }


    @Test(expected = UsernameNotFoundException.class)
    public void shouldReturnUsernameNotFoundExceptionWhenUserNotExist() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        User result = instance.loadUserByUsername(USER_EMAIL);
    }

    @Test
    public void shouldReturnUserWhenUserFound() throws EntityNotFoundException {
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        User result = instance.getUserById(USER_ID);

        assertThat(result).isEqualTo(user);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldReturnEntityNotFoundExceptionWhenUserNotFound() throws EntityNotFoundException {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        User result = instance.getUserById(USER_ID);
    }

    @Test
    public void shouldReturnUserListWhenDriversForBusExist() {
        when(repository.findNotAppointedDriverForBus(any(LocalDate.class))).thenReturn(Collections.singletonList(user));

        List<User> result = instance.getNotAppointDriverForBus();

        assertThat(result).contains(user);
    }

    @Test
    public void shouldReturnEmptyListWhenDriversForBusNotFound() {
        when(repository.findNotAppointedDriverForBus(any(LocalDate.class))).thenReturn(Collections.emptyList());

        List<User> result = instance.getNotAppointDriverForBus();

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnUserWhenSuccessfulSaveNew() throws UserExistException {
        when(userRegisterDto.getFirstName()).thenReturn(USER_FIRST_NAME);
        when(userRegisterDto.getLastName()).thenReturn(USER_LAST_NAME);
        when(userRegisterDto.getOriginFirstName()).thenReturn(USER_ORIGIN_FIRS_NAME);
        when(userRegisterDto.getOriginLastName()).thenReturn(USER_ORIGIN_LAST_NAME);
        when(userRegisterDto.getEmail()).thenReturn(USER_EMAIL);
        when(userRegisterDto.getPassword()).thenReturn(USER_PASS);
        when(repository.save(any(User.class))).thenReturn(user);


        User result = instance.saveNewUser(userRegisterDto);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(user);
    }


}
