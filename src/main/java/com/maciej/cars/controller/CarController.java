package com.maciej.cars.controller;

import com.maciej.cars.config.publisher.RabbitMQJsonProducer;
import com.maciej.cars.config.publisher.RabbitMQProducer;
import com.maciej.cars.dto.car.AddFeatureDTO;
import com.maciej.cars.dto.car.CarDto;
import com.maciej.cars.dto.car.NewCarDto;
import com.maciej.cars.dto.car.UpdateCarDto;
import com.maciej.cars.model.Car;
import com.maciej.cars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    private final RabbitMQProducer rabbitMQProducer;

    private final RabbitMQJsonProducer rabbitMQJsonProducer;

    private final ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody NewCarDto newCarDto) {
        CarDto addedCar = carService.addCar(newCarDto);
        Car car = mapper.map(addedCar, Car.class);
        logger.info("Added {} ",addedCar.toString());
        rabbitMQJsonProducer.sendJsonMessage(car);
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

    @PutMapping("/{carId}")
    public ResponseEntity<CarDto> updateCar(@PathVariable long carId, @RequestBody UpdateCarDto updateCarDto) {
        CarDto updatedCar = carService.updateCar(updateCarDto, carId);
        return ResponseEntity.ok(updatedCar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable long id) {
        rabbitMQProducer.sendMessage("car.get");
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