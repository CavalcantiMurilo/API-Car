package com.cars.carapi.utils.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(Long id){
        super("Registro de carro com id "+ id +" não encontrado");
    }
}
