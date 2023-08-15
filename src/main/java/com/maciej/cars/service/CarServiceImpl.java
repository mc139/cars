package com.maciej.cars.service;

import com.maciej.cars.Car;
import com.maciej.cars.dao.CarRepository;
import com.maciej.cars.dto.CarDto;
import com.maciej.cars.dto.NewCarDto;
import com.maciej.cars.dto.UpdateCarDto;
import com.maciej.cars.exception.CarNotFoundException;
import com.maciej.cars.utils.DummyUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final DummyUtils utils;

    private final ModelMapper mapper;
    //TODO Validator
    //TODO PAGINATION

    public CarDto addCar(NewCarDto newCarDto) {
        Car car = carRepository.saveAndFlush(mapper.map(newCarDto, Car.class));
        return mapper.map(car, CarDto.class);
    }

    public void deleteCar(long carId) {
        deleteCar(carId);
    }

    public CarDto updateCar(UpdateCarDto updateCarDto) {
        //todo implement
        return null;

    }

    public CarDto getCar(long id) {
        Car car =  carRepository.findById(id).orElseThrow(()-> new CarNotFoundException("Car with ID " + id + " not found"));

        return mapper.map(car,CarDto.class);
    }

    @Override
    public void prepareDummyData(long number) {
        utils.populateDatabaseWithCars(number);
    }

    @Override
    public List<CarDto> findAll() {
        return carRepository.findAll().stream().map(c-> mapper.map(c,CarDto.class)).toList();
    }
}
