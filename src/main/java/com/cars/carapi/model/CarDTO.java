package com.cars.carapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


@NoArgsConstructor
@Getter
@Setter
public class CarDTO {

    private Long idCar;
    private String brandCar;
    private String modelCar;
    private String yearCar;
    private String colorCar;

    public CarDTO(Car car){
        BeanUtils.copyProperties(car, this); //conecta Entity e DTO
    }


}
