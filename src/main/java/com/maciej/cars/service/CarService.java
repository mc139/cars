package com.maciej.cars.service;

import com.maciej.cars.dto.car.AddFeatureDTO;
import com.maciej.cars.dto.car.CarDto;
import com.maciej.cars.dto.car.NewCarDto;
import com.maciej.cars.dto.car.UpdateCarDto;

import java.util.List;

public interface CarService {

    CarDto addCar(NewCarDto newCarDto);

    void deleteCar(long carId);

    CarDto updateCar(UpdateCarDto updateCarDto);

    CarDto getCar(long id);

    void prepareDummyData(long number);

    List<CarDto> findAll();

    CarDto addCarFeatures(AddFeatureDTO addFeatureDTO);
}
