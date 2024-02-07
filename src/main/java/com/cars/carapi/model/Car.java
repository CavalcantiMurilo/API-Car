package com.cars.carapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


@Data
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    public Car(CarDTO car){
        BeanUtils.copyProperties(car, this); //conecta Entity e DTO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;

    @Column(length = 15, nullable = false)
    private String brandCar;

    @Column(length = 15, nullable = false)
    private String modelCar;

    @Column(length = 4, nullable = false)
    private String yearCar;

    @Column(length = 10, nullable = false)
    private String colorCar;


}
