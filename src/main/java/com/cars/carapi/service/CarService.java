package com.cars.carapi.service;
import com.cars.carapi.model.CarDTO;
import com.cars.carapi.model.CarMapper;
import com.cars.carapi.repository.ICarRepository;

import com.cars.carapi.utils.exceptions.NotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class CarService {

    @Autowired
    private ICarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    public List<CarDTO> listAll() {
        //pega os registros, mapeia, transforma em DTO e retorna em lista
        return carRepository.findAll().stream().map(car -> carMapper.toDTO(car)).toList();
    }

    public CarDTO createCar(@Valid @NotNull CarDTO car) {
        return carMapper.toDTO(carRepository.save(carMapper.toEntity(car)));
    }

    public CarDTO updateCar(@NotNull @Positive Long id, @Valid CarDTO car) {
        return carRepository.findById(id).map(item ->{
            item.setBrandCar(car.getBrandCar());
            item.setModelCar(car.getModelCar());
            item.setYearCar(car.getYearCar());
            item.setColorCar(car.getColorCar());
            return carMapper.toDTO(carRepository.save(item));
        }).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteCar( @NotNull @Positive Long id) {

        carRepository.delete(carRepository.findById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    public CarDTO listById(@NotNull @Positive Long id) {
        return carRepository.findById(id).map(carMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(id));
    }

}
