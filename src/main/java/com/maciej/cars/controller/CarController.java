package com.maciej.cars.controller;

import com.maciej.cars.dto.car.AddFeatureDTO;
import com.maciej.cars.dto.car.CarDto;
import com.maciej.cars.dto.car.NewCarDto;
import com.maciej.cars.dto.car.UpdateCarDto;
import com.maciej.cars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody NewCarDto newCarDto) {
        CarDto addedCar = carService.addCar(newCarDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCar);
    }

    @PostMapping("/populate/{number}")
    public ResponseEntity<Void> populate(@PathVariable long number) {
        carService.prepareDummyData(number);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<CarDto> updateCar(@RequestBody UpdateCarDto updateCarDto) {
        CarDto updatedCar = carService.updateCar(updateCarDto);
        return ResponseEntity.ok(updatedCar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable long id) {
        CarDto car = carService.getCar(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAll() {
        List<CarDto> cars = carService.findAll();
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/feature")
    public ResponseEntity<CarDto> addCarFeatures(@RequestBody AddFeatureDTO addFeatureDTO) {
        CarDto updatedCar = carService.addCarFeatures(addFeatureDTO);
        return ResponseEntity.ok(updatedCar);
    }

}