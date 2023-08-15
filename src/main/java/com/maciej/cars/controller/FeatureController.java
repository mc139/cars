package com.maciej.cars.controller;

import com.maciej.cars.dto.feature.FeatureCreateDTO;
import com.maciej.cars.dto.feature.FeatureDTO;
import com.maciej.cars.dto.feature.FeatureUpdateDTO;
import com.maciej.cars.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService featureService;

    @PostMapping
    public ResponseEntity<FeatureDTO> createFeature(@RequestBody FeatureCreateDTO createDTO) {
        FeatureDTO createdFeature = featureService.createFeature(createDTO);
        return new ResponseEntity<>(createdFeature, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeatureDTO> updateFeature(
            @PathVariable Long id,
            @RequestBody FeatureUpdateDTO updateDTO) {
        FeatureDTO updatedFeature = featureService.updateFeature(id, updateDTO);
        return new ResponseEntity<>(updatedFeature, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Long id) {
        featureService.deleteFeature(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureDTO> getFeatureById(@PathVariable Long id) {
        FeatureDTO feature = featureService.getFeatureById(id);
        return new ResponseEntity<>(feature, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FeatureDTO>> getAllFeatures() {
        List<FeatureDTO> features = featureService.getAllFeatures();
        return new ResponseEntity<>(features, HttpStatus.OK);
    }

    @PostMapping("/populate/{number}")
    public ResponseEntity<Void> populate(@PathVariable long number) {
        featureService.prepareDummyData(number);
        return ResponseEntity.ok().build();
    }
}
