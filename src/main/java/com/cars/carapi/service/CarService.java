package com.cars.carapi.service;

import com.cars.carapi.model.Car;
import com.cars.carapi.model.CarDTO;
import com.cars.carapi.repository.ICarRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private ICarRepository carRepository;

    public List<CarDTO> listAll() {
        List<Car> car = carRepository.findAll();
        return car.stream().map(CarDTO::new).toList(); //percorre e converte para o DTO
    }

    public void createCar(CarDTO car) {
        Car carEntity = new Car(car);
        carRepository.save(carEntity);
    }

    public CarDTO updateCar(CarDTO car) {
        Car carEntity = new Car(car);
        return new CarDTO(this.carRepository.save(carEntity));
    }

//    public void deleteCar(Long id) {
//
//        Car car = carRepository.findById(id).get();
//        carRepository.delete(car);
//
//    }

    public ResponseEntity<Void> deleteCar(Long id) {

        return carRepository.findById(id).map(item -> {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());

    }

    public CarDTO listById(Long id){
        return new CarDTO(carRepository.findById(id).get());
    }


}
