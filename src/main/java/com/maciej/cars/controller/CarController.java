package com.maciej.cars.controller;

import com.maciej.cars.dto.CarDto;
import com.maciej.cars.dto.NewCarDto;
import com.maciej.cars.dto.UpdateCarDto;
import com.maciej.cars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public CarDto addCar(@RequestBody NewCarDto newCarDto) {
        return carService.addCar(newCarDto);
    }

    @PostMapping("/populate/{number}")
    public void addCar(@PathVariable long number) {
        carService.prepareDummyData(number);
    }

    @PostMapping("/populate2/{number}")
    public void addCar2(@PathVariable long number) {
        carService.prepareDummyData(number);
    }

    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable long carId) {
        carService.deleteCar(carId);
    }

    @PutMapping
    public CarDto updateCar(@RequestBody UpdateCarDto updateCarDto) {
        return carService.updateCar(updateCarDto);
    }

    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable long id) {
        return carService.getCar(id);
    }

    @GetMapping
    public List<CarDto> getAll() {
        return carService.findAll();
    }
}