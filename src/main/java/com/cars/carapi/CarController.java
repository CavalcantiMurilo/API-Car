package com.cars.carapi;

import com.cars.carapi.model.CarDTO;
import com.cars.carapi.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Validated //Executa as validação dos parâmetros
@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @CrossOrigin //resolve problema de CORS
    @GetMapping
    public List<CarDTO> listAll() {
        return carService.listAll();
    }

    @CrossOrigin
    @PostMapping
    public CarDTO createCar(@RequestBody @Valid @NotNull CarDTO car) {
        return carService.createCar(car);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public CarDTO updateCar(@PathVariable @NotNull @Positive Long id,@RequestBody @Valid CarDTO car) {
        return carService.updateCar(id, car);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable @NotNull @Positive Long id) {
        carService.deleteCar(id);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public CarDTO listById(@PathVariable @NotNull @Positive Long id) {
        return carService.listById(id);
    }
}
