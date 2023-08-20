package com.maciej.cars.matchers;

import com.maciej.cars.dto.car.NewCarDto;
import com.maciej.cars.model.Car;
import org.mockito.ArgumentMatcher;
import org.modelmapper.ModelMapper;

public class ModelMapperArgumentMatcher implements ArgumentMatcher<Car> {

    private final ModelMapper modelMapper;
    private final NewCarDto newCarDto;

    public ModelMapperArgumentMatcher(ModelMapper modelMapper, NewCarDto newCarDto) {
        this.modelMapper = modelMapper;
        this.newCarDto = newCarDto;
    }

    @Override
    public boolean matches(Car  car) {
        // Map the NewCarDto to a Car object using the ModelMapper
        Car mappedCar = modelMapper.map(newCarDto, Car.class);

        // Compare attributes of the mapped Car object with the actual Car object
        boolean yearOfManufactureMatches = car.getYearOfManufacture() == mappedCar.getYearOfManufacture();
        boolean mileageMatches = car.getMileage() == mappedCar.getMileage();
        boolean basePriceMatches = car.getBasePrice().equals(mappedCar.getBasePrice());
        boolean totalPriceMatches = car.getTotalPrice().equals(mappedCar.getTotalPrice());
        boolean makeMatches = car.getMake().equals(mappedCar.getMake());
        boolean descriptionMatches = car.getDescription().equals(mappedCar.getDescription());

        // Compare features (assuming Feature objects have appropriate equals/hashCode)
        boolean featuresMatch = car.getFeatures().equals(mappedCar.getFeatures());

        // Return true if all attributes match
        return yearOfManufactureMatches && mileageMatches && basePriceMatches &&
                totalPriceMatches && makeMatches && descriptionMatches && featuresMatch;
    }


}
