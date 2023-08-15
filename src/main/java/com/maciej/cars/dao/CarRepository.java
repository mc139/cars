package com.maciej.cars.dao;

import com.maciej.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
