package br.com.api;

import br.com.api.model.Car;
import br.com.api.repository.CarRepository;
import br.com.api.service.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car testCar;

    @BeforeEach
    void setUp() {
        testCar = new Car();
        testCar.setId(1L);
        testCar.setModel("Test Model");
        testCar.setLicensePlate("TEST123");
        testCar.setColor("Red");
        testCar.setYear(2020);
    }

    @Test
    void createCar() {
        when(carRepository.save(any(Car.class))).thenReturn(testCar);

        Car savedCar = carService.createCar(testCar);

        assertNotNull(savedCar);
        assertEquals(testCar.getId(), savedCar.getId());
        assertEquals(testCar.getModel(), savedCar.getModel());
        assertEquals(testCar.getLicensePlate(), savedCar.getLicensePlate());
        assertEquals(testCar.getColor(), savedCar.getColor());
        assertEquals(testCar.getYear(), savedCar.getYear());

        verify(carRepository, times(1)).save(testCar);
    }

    @Test
    void getAllCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(testCar);

        when(carRepository.findAll()).thenReturn(cars);

        List<Car> retrievedCars = carService.getAllCars();

        assertNotNull(retrievedCars);
        assertEquals(1, retrievedCars.size());
        assertEquals(testCar, retrievedCars.get(0));

        verify(carRepository, times(1)).findAll();
    }

    @Test
    void getCarById_Exists() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(testCar));

        Car retrievedCar = carService.getCarById(1L);

        assertNotNull(retrievedCar);
        assertEquals(testCar, retrievedCar);

        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void getCarById_NotExists() {
        when(carRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> carService.getCarById(2L));

        verify(carRepository, times(1)).findById(2L);
    }

}

