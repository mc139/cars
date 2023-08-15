package com.maciej.cars.dto.feature;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FeatureCreateDTO {
    private String name;
    private BigDecimal price;
}
