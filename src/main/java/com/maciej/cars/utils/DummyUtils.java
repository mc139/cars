package com.maciej.cars.utils;

import com.github.javafaker.Faker;
import com.maciej.cars.dao.CarRepository;
import com.maciej.cars.dao.FeatureRepository;
import com.maciej.cars.model.Car;
import com.maciej.cars.model.Feature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DummyUtils {

    private final CarRepository carRepository;
    private final Faker faker;
    private final FeatureRepository featureRepository;

    public void populateDatabaseWithCars(long number) {

        for (int i = 0; i < number; i++) {
            Car car = prepareCar();
            carRepository.saveAndFlush(car);
        }

    }

    private Car prepareCar() {
        Car car = new Car();
        car.setYearOfManufacture(faker.number().numberBetween(1960, 2023));
        car.setMileage(faker.random().nextInt(1, 1111111111));
        car.setMake(faker.animal().name());
        car.setBasePrice(BigDecimal.valueOf(faker.number().numberBetween(10002, 53777)));
        car.setDescription(faker.howIMetYourMother().catchPhrase());
        return car;
    }

    public void populateDatabaseWithFeatures(long number) {

        for (int i = 0; i < number; i++) {
            Feature feature = prepareFeature();
            featureRepository.saveAndFlush(feature);
        }
    }

    private Feature prepareFeature() {
        Feature feature = new Feature();
        feature.setName(faker.superhero().name());
        feature.setPrice(BigDecimal.valueOf(faker.number().numberBetween(1000, 10000)));
        return feature;
    }
}
