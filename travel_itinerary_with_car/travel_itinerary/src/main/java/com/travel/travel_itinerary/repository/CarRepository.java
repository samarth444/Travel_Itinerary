package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Search by location only (case-insensitive, partial match)
    List<Car> findByLocationContainingIgnoreCase(String location);

    // Search by location AND type (both partial, case-insensitive)
    List<Car> findByLocationContainingIgnoreCaseAndTypeContainingIgnoreCase(String location, String type);
}
