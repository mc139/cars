package com.maciej.cars.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Long id;

    private int yearOfManufacture;

    private int mileage;

    private BigDecimal basePrice;

    private BigDecimal totalPrice;

    private String make;

    private String description;

}
