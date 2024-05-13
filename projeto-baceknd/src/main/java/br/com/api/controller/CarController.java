package br.com.api.controller;

import br.com.api.model.Car;
import br.com.api.model.User;
import br.com.api.service.CarServiceImpl;
import br.com.api.service.JwtTokenService;
import br.com.api.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtTokenService jwtTokenService;



    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(@RequestHeader("Authorization") String token) {
        // Extrair o logii do usuário do token JWT
        Claims claims = jwtTokenService.parseToken(token.replace("Bearer ", ""));
        User user = userService.findByLogin(claims.getSubject());


        if (user == null) {
            // Token inválido, retornar status 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Car> cars = userService.getUserById(user.getId()).getCars();
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<List<Car>> addCar(@RequestHeader("Authorization") String token, @RequestBody Car c) {

        // Extrair o logii do usuário do token JWT
        Claims claims = jwtTokenService.parseToken(token.replace("Bearer ", ""));
        User user = userService.findByLogin(claims.getSubject());

        if (user == null) {
            // Token inválido, retornar status 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Car> cars = user.getCars();

        c.validate();

        carService.validLicensePlateAlreadyExists(cars, c.getLicensePlate());

        try {
            cars.add(c);
            user.setCars(cars);
            userService.updateUserById(user.getId(), user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@RequestHeader("Authorization") String token, @PathVariable Long id) {

        // Extrair o logii do usuário do token JWT
        Claims claims = jwtTokenService.parseToken(token.replace("Bearer ", ""));
        User user = userService.findByLogin(claims.getSubject());

        if (user == null) {
            // Token inválido, retornar status 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Implemente a lógica para obter um carro específico do usuário logado com base no token JWT e no ID do carro
        Car car = carService.getCarById(id);
        if (car != null) {
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@RequestHeader("Authorization") String token, @PathVariable Long id) {

        // Extrair o logii do usuário do token JWT
        Claims claims = jwtTokenService.parseToken(token.replace("Bearer ", ""));
        User user = userService.findByLogin(claims.getSubject());

        if (user == null) {
            // Token inválido, retornar status 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Implemente a lógica para remover um carro específico do usuário logado com base no token JWT e no ID do carro
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCar(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody Car car) {

        // Extrair o logii do usuário do token JWT
        Claims claims = jwtTokenService.parseToken(token.replace("Bearer ", ""));
        User user = userService.findByLogin(claims.getSubject());

        if (user == null) {
            // Token inválido, retornar status 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Implemente a lógica para atualizar um carro específico do usuário logado com base no token JWT e no ID do carro
        carService.updateCarById(id, car);
        return ResponseEntity.noContent().build();
    }
}
