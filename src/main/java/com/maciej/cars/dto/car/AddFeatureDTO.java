package com.maciej.cars.dto.car;

import lombok.Data;

import java.util.List;

@Data
public class AddFeatureDTO {
    private Long carId;
    private List<Long> featureIds;
}