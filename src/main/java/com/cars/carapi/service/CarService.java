package com.cars.carapi.service;

import com.cars.carapi.model.Car;
import com.cars.carapi.repository.ICarRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private ICarRepository carRepository;

    public List<Car> listAll(){
        List<Car> car = carRepository.findAll();
        return car.stream().toList();
    }

    public void createCar(Car car){
        carRepository.save(car);
    }

    public Car updateCar(Car car){
        return this.carRepository.save(car);
    }

    public void deleteCar(Long id){
        Car car = carRepository.findById(id).get();
        carRepository.delete(car);
    }



//    @Transactional
//    public Car createCar(Car obj) {
//        obj.setIdCar(null); //garante que cria um novo id
//        obj = this.carRepository.save(obj);
//
//        return obj;
//    }

//    @Transactional
//    public Car updateCar(Car obj) {
//        Car newObj = findById(obj.getIdCar());
//
//        newObj.setModelCar(obj.getBrandCar());
//
//        return this.carRepository.save(newObj);
//    }
//
//    public void deleteCar(Long id) {
//       findById(id);
//
//       this.carRepository.deleteById(id);
//    }



}
