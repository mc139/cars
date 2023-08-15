package com.maciej.cars.service;

import com.maciej.cars.dto.feature.FeatureCreateDTO;
import com.maciej.cars.dto.feature.FeatureDTO;
import com.maciej.cars.dto.feature.FeatureUpdateDTO;

import java.util.List;

public interface FeatureService {

    FeatureDTO createFeature(FeatureCreateDTO createDTO);
    FeatureDTO updateFeature(Long id, FeatureUpdateDTO updateDTO);
    void deleteFeature(Long id);
    FeatureDTO getFeatureById(Long id);
    List<FeatureDTO> getAllFeatures();

    void prepareDummyData(long number);
}

