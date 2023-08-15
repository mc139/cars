package com.maciej.cars.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class UpdateCarDto {
    private int yearOfManufacture;

    private int mileage;

    private BigDecimal basePrice;

    private String make;

    private String description;

    private Set<Long> featureIds;

}