package com.cars.carapi;

import com.cars.carapi.model.Car;
import com.cars.carapi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> listAll(){
        return carService.listAll();
    }

    @PostMapping
    public void createCar(@RequestBody Car car){
        carService.createCar(car);
    }

    @PutMapping
    public Car updateCar( @RequestBody Car car){
        return carService.updateCar(car);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping() //Entidade de resposta da API
//    public List<Car> list(){
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> createCar(@RequestBody Car obj){
//        this.carService.createCar(obj);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//            .path("/{id}").buildAndExpand(obj.getIdCar()).toUri();
//        return ResponseEntity.created(uri).build();
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<Void> updateCar(@RequestBody Car obj, @PathVariable Long id){
//        obj.setIdCar(id);
//        this.carService.updateCar(obj);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
//        this.carService.deleteCar(id);
//        return  ResponseEntity.noContent().build();
//    }
}
