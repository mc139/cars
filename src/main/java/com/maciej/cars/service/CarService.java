package com.maciej.cars.service;

import com.maciej.cars.dto.CarDto;
import com.maciej.cars.dto.NewCarDto;
import com.maciej.cars.dto.UpdateCarDto;

import java.util.List;

public interface CarService {

     CarDto addCar(NewCarDto newCarDto);

     void deleteCar(long carId);

     CarDto updateCar(UpdateCarDto updateCarDto);

     CarDto getCar(long id);

     void prepareDummyData(long number);

     List<CarDto> findAll();
}
