package com.maciej.cars.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCarDto {
    private int yearOfManufacture;
    private int mileage;
    private BigDecimal basePrice;
    private String make;
    private String description;

}