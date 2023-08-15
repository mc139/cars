package com.maciej.cars.dto.feature;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FeatureUpdateDTO {
    private String name;
    private BigDecimal price;
}
