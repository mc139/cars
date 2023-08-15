package com.maciej.cars.dto.car;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewCarDto {
    private int yearOfManufacture;
    private int mileage;
    private BigDecimal basePrice;
    private String make;
    private String description;

}