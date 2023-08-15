package com.maciej.cars;

import com.maciej.cars.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseEntity {

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Set<Feature> features;

    private static BigDecimal calculateTotalPrice(final Car car, final Set<Feature> features) {
        return features.stream()
                .map(Feature::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(car.getBasePrice());

    }
}
