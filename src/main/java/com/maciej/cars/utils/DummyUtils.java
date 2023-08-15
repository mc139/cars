package com.maciej.cars.utils;

import com.github.javafaker.Faker;
import com.maciej.cars.Car;
import com.maciej.cars.dao.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DummyUtils {

    private final CarRepository carRepository;
    private final Faker faker;

    public void populateDatabaseWithCars(long number) {



        for (int i = 0; i < number; i++) {
            Car car = prepareCar();
            carRepository.saveAndFlush(car);
        }

    }

    private Car prepareCar() {
        Car car = new Car();
        car.setYearOfManufacture(faker.number().numberBetween(1960,2023));
        car.setMileage(faker.random().nextInt(1,1111111111));
        car.setMake(faker.animal().name());
        car.setBasePrice(BigDecimal.valueOf(faker.number().numberBetween(10002,53777)));
        car.setDescription(faker.howIMetYourMother().catchPhrase());
        return car;
    }
}
