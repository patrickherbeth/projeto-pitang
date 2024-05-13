package br.com.api.service;


import br.com.api.model.User;
import br.com.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void createUser(User user) throws Exception {
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new Exception("Login already exists");
        }
        // You may need to hash the password before saving it
        userRepository.save(user);
    }

    @Nullable
    public ResponseEntity<String> validateUser(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid fields");
        }

        // Verificar se o email já está em uso
        if (existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        // Verificar se o login já está em uso
        if (existsByLogin(user.getLogin()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Login already exists");
        }
        return null;
    }

    private User existsByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    private boolean existsByEmail(String email) {
       return userRepository.findByEmail(email).get() != null;
    }

    private User getEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public void deleteUserById(Long id) throws Exception {
        if (!userRepository.existsById(id)) {
            throw new Exception("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public void updateUserById(Long id, User user) throws Exception {
        if (!userRepository.existsById(id)) {
            throw new Exception("User not found with id: " + id);
        }

        user.setId(id);
        userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User getCurrentUser(String name) {
        return userRepository.findByLogin(name);
    }

    public boolean isValidCredentials(User userCredentials) throws Exception {

        User user  = userRepository.findByLogin(userCredentials.getLogin());

        return user.getLogin().equals(userCredentials.getLogin()) && user.getPassword().equals(userCredentials.getPassword());
    }
}

