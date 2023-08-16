package com.maciej.cars.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maciej.cars.dto.car.AddFeatureDTO;
import com.maciej.cars.dto.car.CarDto;
import com.maciej.cars.dto.car.NewCarDto;
import com.maciej.cars.dto.car.UpdateCarDto;
import com.maciej.cars.service.CarService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController underTest;

    private final Gson gson = new Gson();

    private  MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build(); // Initialize MockMvc
    }


    @Test
    void addCar() throws Exception {
        // Prepare the input DTO using Lombok's builder
        // Prepare the input DTO using Lombok's builder
        NewCarDto newCarDto = NewCarDto.builder()
                .yearOfManufacture(2022)
                .mileage(10000)
                .basePrice(BigDecimal.valueOf(25000))
                .make("Toyota")
                .description("New car description")
                .build();

        // Prepare the expected CarDto response using Lombok's builder
        CarDto expectedCarDto = CarDto.builder()
                .id(1L) // Assuming you have an ID field in CarDto
                .yearOfManufacture(2022)
                .mileage(10000)
                .basePrice(BigDecimal.valueOf(25000))
                .make("Toyota")
                .description("New car description")
                .build();

        // Mock the service's behavior
        when(carService.addCar(any(NewCarDto.class))).thenReturn(expectedCarDto);

        // Perform the controller action using MockMvc
        MvcResult result = mockMvc.perform(post("/car")
                .content(gson.toJson(newCarDto)) // Fixed line
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        // Use Gson to deserialize the response body
        CarDto responseCarDto = gson.fromJson(result.getResponse().getContentAsString(), CarDto.class);

        // Verify the service method was called with the correct argument
        verify(carService).addCar(newCarDto);

        // Verify the response body matches the expected response
        assertThat(responseCarDto, equalTo(expectedCarDto));
    }


    @Test
    void deleteCar() throws Exception {
        long carIdToDelete = 1L;

        mockMvc.perform(delete("/car/{carId}", carIdToDelete))
                .andExpect(status().isNoContent());

        // Verify that the deleteCar method in carService was called with the correct argument
        verify(carService).deleteCar(carIdToDelete);

    }

    @Test
    void updateCar() throws Exception {
        long carIdToUpdate = 1L;

        UpdateCarDto updateCarDto = UpdateCarDto.builder()
                .yearOfManufacture(2023)
                .mileage(15000)
                .basePrice(BigDecimal.valueOf(28000))
                .make("Honda")
                .description("Updated car description")
                .build();

        CarDto updatedCarDto = CarDto.builder()
                .id(carIdToUpdate)
                .yearOfManufacture(2023)
                .mileage(15000)
                .basePrice(BigDecimal.valueOf(28000))
                .make("Honda")
                .description("Updated car description")
                .build();

        when(carService.updateCar(any(UpdateCarDto.class), eq(carIdToUpdate))).thenReturn(updatedCarDto);

        MvcResult result = mockMvc.perform(
                        put("/car/{carId}", carIdToUpdate)
                                .content(gson.toJson(updateCarDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        CarDto responseCarDto = gson.fromJson(result.getResponse().getContentAsString(), CarDto.class);

        verify(carService).updateCar(updateCarDto, carIdToUpdate);

        assertThat(responseCarDto, equalTo(updatedCarDto));
    }

    @Test
    void getCar() throws Exception {
        long carId = 1L;

        CarDto expectedCarDto = CarDto.builder()
                .id(carId)
                .yearOfManufacture(2022)
                .mileage(10000)
                .basePrice(BigDecimal.valueOf(25000))
                .make("Toyota")
                .description("Car description")
                .build();

        when(carService.getCar(eq(carId))).thenReturn(expectedCarDto);

        MvcResult result = mockMvc.perform(get("/car/{id}", carId))
                .andExpect(status().isOk())
                .andReturn();

        CarDto responseCarDto = gson.fromJson(result.getResponse().getContentAsString(), CarDto.class);

        verify(carService).getCar(carId);

        assertThat(responseCarDto, equalTo(expectedCarDto));
    }

    @Test
    void getAll() throws Exception {
        List<CarDto> expectedCars = createExampleCarDtoList(102);

        when(carService.findAll()).thenReturn(expectedCars);

        MvcResult result = mockMvc.perform(get("/car"))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        List<CarDto> responseCars = gson.fromJson(responseContent, new TypeToken<List<CarDto>>(){}.getType());

        verify(carService).findAll();

        assertThat(responseCars, equalTo(expectedCars));

    }

    @Test
    void addCarFeatures() {
    }
    private List<CarDto> createExampleCarDtoList(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> CarDto.builder()
                        .id((long) (i + 1))
                        .yearOfManufacture(2022)
                        .mileage(10000)
                        .basePrice(BigDecimal.valueOf(25000))
                        .make("Toyota")
                        .description("Car description")
                        .build())
                .collect(Collectors.toList());
    }
}