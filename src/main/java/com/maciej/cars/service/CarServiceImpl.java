package com.maciej.cars.service;

import com.maciej.cars.dao.FeatureRepository;
import com.maciej.cars.dto.car.AddFeatureDTO;
import com.maciej.cars.model.Car;
import com.maciej.cars.dao.CarRepository;
import com.maciej.cars.dto.car.CarDto;
import com.maciej.cars.dto.car.NewCarDto;
import com.maciej.cars.dto.car.UpdateCarDto;
import com.maciej.cars.exception.CarNotFoundException;
import com.maciej.cars.model.Feature;
import com.maciej.cars.utils.DummyUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final FeatureRepository featureRepository;

    private final DummyUtils utils;

    private final ModelMapper mapper;

    public CarDto addCar(NewCarDto newCarDto) {
        Car car = carRepository.saveAndFlush(mapper.map(newCarDto, Car.class));
        return mapper.map(car, CarDto.class);
    }

    public void deleteCar(long carId) {
        deleteCar(carId);
    }

    public CarDto updateCar(UpdateCarDto updateCarDto,long carId) {
        Car existingCar = carRepository.findById(carId).orElseThrow(()-> new CarNotFoundException("Car not found with id: " + carId));

        existingCar.setDescription(updateCarDto.getDescription());
        existingCar.setYearOfManufacture(updateCarDto.getYearOfManufacture());
        existingCar.setMileage(updateCarDto.getMileage());
        existingCar.setBasePrice(updateCarDto.getBasePrice());
        existingCar.setMake(updateCarDto.getMake());

        return mapper.map(carRepository.saveAndFlush(existingCar), CarDto.class);

    }

    public CarDto getCar(long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car with ID " + id + " not found"));

        return mapper.map(car, CarDto.class);
    }

    @Override
    public void prepareDummyData(long number) {
        utils.populateDatabaseWithCars(number);
    }

    @Override
    public List<CarDto> findAll() {
        return carRepository.findAll().stream().map(c -> mapper.map(c, CarDto.class)).toList();
    }

    @Override
    public CarDto addCarFeatures(AddFeatureDTO addFeatureDTO) {
        Long carId = addFeatureDTO.getCarId();
        List<Long> featureIds = addFeatureDTO.getFeatureIds();

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car not found id {" + carId + "}"));

        List<Feature> featuresToAdd
                = featureRepository.findAllById(featureIds);
        featuresToAdd.forEach(car::addFeature);

        Car updatedCar = carRepository.save(car);
        return mapper.map(updatedCar, CarDto.class);
    }

}
