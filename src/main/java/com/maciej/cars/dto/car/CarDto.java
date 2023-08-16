package com.maciej.cars.dto.car;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CarDto {
    private Long id;
    private int yearOfManufacture;
    private int mileage;
    private BigDecimal basePrice;
    private BigDecimal totalPrice;
    private String make;
    private String description;

}
