package br.com.api;

import br.com.api.model.User;
import br.com.api.repository.UserRepository;
import br.com.api.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertEquals(userList, result);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals(user, result);
    }

    @Test
    public void testCreateUser_Success() throws Exception {
        User user = new User();
        user.setLogin("test");

        when(userRepository.existsByLogin("test")).thenReturn(false);

        assertDoesNotThrow(() -> userService.createUser(user));
    }

    @Test
    public void testCreateUser_LoginAlreadyExists() {
        User user = new User();
        user.setLogin("existing");

        when(userRepository.existsByLogin("existing")).thenReturn(true);

        assertThrows(Exception.class, () -> userService.createUser(user));
    }

    @Test
    public void testValidateUser_InvalidFields() {
        User user = new User();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<String> result = userService.validateUser(user, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    // Additional tests for other methods can be added similarly
}

