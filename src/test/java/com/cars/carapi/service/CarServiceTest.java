package com.cars.carapi.service;

import com.cars.carapi.model.Car;
import com.cars.carapi.model.CarDTO;
import com.cars.carapi.model.CarMapper;
import com.cars.carapi.repository.ICarRepository;
import com.cars.carapi.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Mock
    private ICarRepository carRepository;

    @Mock
    private CarMapper carMapper;
    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp(){
        //inicialização dos Mocks
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create a car in Database succesfully")
    void createCar() {

        CarDTO carDTO = new CarDTO(1L, "Toyota", "Corolla", 2022, "Red");
        Car carEntity = new Car(1L, "Toyota", "Corolla", 2022, "Red");

        //O que ele deve retornar quando o método do Mock for chamado
        when(carMapper.toDTO(carEntity)).thenReturn(carDTO);
        when(carRepository.save(carEntity)).thenReturn(carEntity);
        when(carMapper.toEntity(carDTO)).thenReturn(carEntity);

        CarDTO createdCarDTO = carService.createCar(carDTO);

        //Verificando se o proposto aconteceu
        verify(carMapper, times(1)).toEntity(carDTO);
        verify(carRepository, times(1)).save(carEntity);
        verify(carMapper, times(1)).toDTO(carEntity);

        //Verifica se o valor retornado não é nulo para dar sucesso
        assertNotNull(createdCarDTO);
        //Compara para ver se os valores de saídas foram os esperados.
        assertEquals(carDTO, createdCarDTO);
    }

    @Test
    @DisplayName("Should get all registered cars in Database")
    void listAll() {

        Car car1 = new Car(1L, "Chevrolet", "Prisma", 2018, "Red");
        Car car2 = new Car(2L, "Nissan", "Kicks", 2020, "Brown");
        List<Car> carList = new ArrayList<Car>();
        carList.add(car1);
        carList.add(car2);

        //O que ele deve retornar quando o método do Mock for chamado
        when(carRepository.findAll()).thenReturn(carList);

        CarDTO carDto1 = new CarDTO(1L, "Chevrolet", "Prisma", 2018, "Silver");
        CarDTO carDto2 = new CarDTO(2L, "Nissan", "Kicks", 2020, "Brown");
        List<CarDTO> dtoList = new ArrayList<CarDTO>();

        dtoList.add(carDto1);
        dtoList.add(carDto2);

        //Testando a transformação de Entity para DTO
        when(carMapper.toDTO(car1)).thenReturn(carDto1);
        when(carMapper.toDTO(car2)).thenReturn(carDto2);

        List<CarDTO> result = carService.listAll();


        verify(carRepository).findAll();

        //Verificando que a atuação do carMapper ocorre para cada elemento encontrado
        verify(carMapper, times(carList.size())).toDTO(any());


        //Assegura que o resultado não veio nulo
        assertNotNull(result);

        //Verifica a igualdade entre o resultado feito e o esperado
        assertEquals(dtoList.size(), result.size());

    }

    @Test
    @DisplayName("Should update a registered car by informed id")
    void updateCarSuccess() {
        CarDTO updatedCarDTO = new CarDTO(1L, "Chevrolet", "Prisma", 2018, "Silver");
        Car updatedCarEntity = new Car(1L, "Chevrolet", "Prisma", 2018, "Silver");

        when(carRepository.findById(1L)).thenReturn(Optional.of(updatedCarEntity));
        when(carRepository.save(updatedCarEntity)).thenReturn(updatedCarEntity);
        when(carMapper.toDTO(updatedCarEntity)).thenReturn(updatedCarDTO);

        CarDTO result = carService.updateCar(1L, updatedCarDTO);

        verify(carRepository).save(updatedCarEntity);
        verify(carMapper).toDTO(updatedCarEntity);

        assertNotNull(result);
        assertEquals(updatedCarDTO, result);
    }

    @Test
    @DisplayName("Should throw exception when informed id is not found")
    void updateCarFailure() {

        CarDTO updatedCarDTO = new CarDTO(1L, "Chevrolet", "Prisma", 2018, "Silver");

        //Retorna vazio ao não encontro do ID
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        //Assegura que a exceção de NotFoundException será lançada
        assertThrows(NotFoundException.class, () -> {
            carService.updateCar(1L, updatedCarDTO);
        });
    }

    @Test
    @DisplayName("Should be able to delete an registered ID succesfully")
    void deleteCarSuccess() {
        Car car = new Car(1L, "Chevrolet", "Prisma", 2018, "Silver");

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        assertDoesNotThrow(() -> {
            carService.deleteCar(1L);
        });

        verify(carRepository).findById(1L);
        verify(carRepository).delete(car);
    }

    @Test
    @DisplayName("Should throw exception when informed id is not found")
    void deleteCarFailure() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        // Agora esperando que ele lance uma exceção
        assertThrows(NotFoundException.class, () -> {
            carService.deleteCar(1L);
        });

        verify(carRepository).findById(1L);
    }

    @Test
    @DisplayName("Should succesfully list registered cars by id")
    void listByIdSuccess(){
        Car car = new Car(1L, "Chevrolet", "Prisma", 2018, "Silver");
        CarDTO carDto = new CarDTO(2L, "Chevrolet", "Prisma", 2018, "White");


        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carMapper.toDTO(car)).thenReturn(carDto);

        CarDTO result = carService.listById(1L);

        verify(carRepository).findById(1L);
        verify(carMapper).toDTO(car);

        assertNotNull(result);
        assertEquals(carDto, result);
    }

    @Test
    @DisplayName("Should throw right exception when id not founded")
    void ListByIdFailure() {

        Car car = new Car(1L, "Chevrolet", "Prisma", 2018, "Silver");

        //Setando um id não existente
        when(carRepository.findById(2L)).thenReturn(Optional.empty());

        //Assegura que a exceção de NotFoundException será lançada
        assertThrows(NotFoundException.class, () -> {
            carService.listById(2L);
        });
    }
}