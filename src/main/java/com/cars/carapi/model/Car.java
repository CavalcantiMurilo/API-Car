package com.cars.carapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;

    @Column(nullable = false)
    private String brandCar;

    @Column(nullable = false)
    private String modelCar;

    @Column(nullable = false)
    private String yearCar;

    @Column(nullable = false)
    private String colorCar;


}
