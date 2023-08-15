package com.maciej.cars.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
public class Car extends BaseEntity {

    public Car(int yearOfManufacture, int mileage, BigDecimal basePrice, BigDecimal totalPrice, String make, String description, Set<Feature> features) {
        this.yearOfManufacture = yearOfManufacture;
        this.mileage = mileage;
        this.basePrice = basePrice;
        this.make = make;
        this.description = description;
        this.features = features;
        this.totalPrice = calculateTotalPrice(this,this.features);
    }

    @Column(name = "year_of_manufacture")
    private int yearOfManufacture;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "base_price")
    @Min(value = 1000, message = "Base price must be at least 1000")
    @Max(value = 100000, message = "Base price cannot exceed 100000")
    private BigDecimal basePrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "make")
    private String make;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Set<Feature> features = new HashSet<>();

    public void addFeature(Feature feature) {
        features.add(feature);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        this.totalPrice = calculateTotalPrice(this,this.features);
    }

    private static BigDecimal calculateTotalPrice(final Car car, final Set<Feature> features) {
        return features.stream()
                .map(Feature::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(car.getBasePrice());

    }
}
