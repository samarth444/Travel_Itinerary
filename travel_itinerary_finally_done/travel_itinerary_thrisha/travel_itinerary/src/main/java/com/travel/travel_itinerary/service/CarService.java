package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Car;
import com.travel.travel_itinerary.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    /** Fetch all cars */
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /** Fetch one car by ID */
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    /**
     * Search by location & optionally type.
     * If type is blank, search only by location.
     */
    public List<Car> searchCars(String location, String type) {
        if (type == null || type.trim().isEmpty()) {
            return carRepository.findByLocationContainingIgnoreCase(location);
        }
        return carRepository.findByLocationContainingIgnoreCaseAndTypeContainingIgnoreCase(location, type);
    }
}