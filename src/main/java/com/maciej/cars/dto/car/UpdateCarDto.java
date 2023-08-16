package com.maciej.cars.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarDto {

    private int yearOfManufacture;

    private int mileage;

    private BigDecimal basePrice;

    private String make;

    private String description;

    private Set<Long> featureIds;

}