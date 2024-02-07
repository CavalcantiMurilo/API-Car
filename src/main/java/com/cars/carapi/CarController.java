package com.cars.carapi;

import com.cars.carapi.model.Car;
import com.cars.carapi.model.CarDTO;
import com.cars.carapi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @CrossOrigin
    @GetMapping
    public List<CarDTO> listAll() {
        return carService.listAll();
    }

    @CrossOrigin
    @PostMapping
    public void createCar(@RequestBody CarDTO car) {
        carService.createCar(car);
    }

    @PutMapping
    public CarDTO updateCar(@RequestBody CarDTO car) {
        return carService.updateCar(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public CarDTO listById(@PathVariable Long id) {
        return carService.listById(id);
    }
}
