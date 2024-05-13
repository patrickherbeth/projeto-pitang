package br.com.api.controller;

import br.com.api.model.User;
import br.com.api.service.JwtTokenService;
import br.com.api.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/generate-token")
    public ResponseEntity<String> generateToken(@RequestBody User userCredentials) {

        try {
            if (userService.isValidCredentials(userCredentials)) {
                String token = jwtTokenService.generateToken(userCredentials.getLogin());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/api/user-info")
    public ResponseEntity<String> getUserInfo(@RequestHeader("Authorization") String token) {

        Claims claims = jwtTokenService.parseToken(token.replace("Bearer ", ""));
        if (claims != null) {
            // As informações do usuário estão disponíveis no objeto Claims
            return ResponseEntity.ok("Username: " + claims.getSubject());
        } else {
            return ResponseEntity.badRequest().body("Token inválido ou expirado");
        }
    }

}

