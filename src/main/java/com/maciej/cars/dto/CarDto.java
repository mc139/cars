package com.maciej.cars.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarDto {
    private Long id;
    private int yearOfManufacture;
    private int mileage;
    private BigDecimal basePrice;
    private String make;
    private String description;

}
