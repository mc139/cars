package com.maciej.cars.service;

import com.maciej.cars.dao.FeatureRepository;
import com.maciej.cars.dto.feature.FeatureCreateDTO;
import com.maciej.cars.dto.feature.FeatureDTO;
import com.maciej.cars.dto.feature.FeatureUpdateDTO;
import com.maciej.cars.exception.FeatureNotFoundException;
import com.maciej.cars.model.Feature;
import com.maciej.cars.utils.DummyUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    private final ModelMapper modelMapper;

    private final DummyUtils utils;
    @Override
    public FeatureDTO createFeature(FeatureCreateDTO createDTO) {
        Feature feature = modelMapper.map(createDTO, Feature.class);
        Feature savedFeature = featureRepository.save(feature);
        return modelMapper.map(savedFeature, FeatureDTO.class);
    }

    @Override
    public FeatureDTO updateFeature(Long id, FeatureUpdateDTO updateDTO) {
        Feature existingFeature = featureRepository.findById(id)
                .orElseThrow(() -> new FeatureNotFoundException("Feature not found"));
        modelMapper.map(updateDTO, existingFeature);
        Feature updatedFeature = featureRepository.save(existingFeature);
        return modelMapper.map(updatedFeature, FeatureDTO.class);
    }

    @Override
    public void deleteFeature(Long id) {
        featureRepository.deleteById(id);
    }

    @Override
    public FeatureDTO getFeatureById(Long id) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(() -> new FeatureNotFoundException("Feature not found"));
        return modelMapper.map(feature, FeatureDTO.class);
    }

    @Override
    public List<FeatureDTO> getAllFeatures() {
        List<Feature> features = featureRepository.findAll();
        return features.stream()
                .map(feature -> modelMapper.map(feature, FeatureDTO.class))
                .toList();
    }

    @Override
    public void prepareDummyData(long number) {
        utils.populateDatabaseWithFeatures(number);
    }
}
