package com.cars.carapi.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDTO {


    private Long idCar;

    private String brandCar;

    private String modelCar;

    private Integer yearCar;

    private String colorCar;

}
