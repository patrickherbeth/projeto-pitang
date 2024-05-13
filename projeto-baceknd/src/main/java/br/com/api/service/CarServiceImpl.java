package br.com.api.service;

import br.com.api.model.Car;
import br.com.api.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CarServiceImpl {

    @Autowired
    private CarRepository carRepository;

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    public void updateCarById(Long id, Car carDto) {
        Car car = getCarById(id);
        car.setModel(carDto.getModel());
        car.setLicensePlate(carDto.getLicensePlate());
        car.setColor(carDto.getColor());
        car.setYear(carDto.getYear());
        carRepository.save(car);
    }

    public void validLicensePlateAlreadyExists(List<Car> cars, String plate) {
       boolean value =  cars.stream()
                .anyMatch(car -> Objects.equals(car.getLicensePlate(), plate));

       if(value) {
           throw new RuntimeException("License plate already exists");
       }

    }

    public void validateCarFields(List<Car> cars) {
        boolean invalidFields = cars.stream()
                .anyMatch(this::hasInvalidFields);

        if (invalidFields) {
            throw new RuntimeException("Invalid fields");
        }
    }

    private boolean hasInvalidFields(Car car) {
        return car.getLicensePlate() == null || car.getColor() == null ||
                car.getModel() == null || car.getModel().isEmpty() ||
                car.getYear() <= 0; // Exemplo de outra validação (ano negativo ou zero)
    }
}
