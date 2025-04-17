package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    // Find flights by locations
    List<Flight> findByFromLocationAndToLocation(String fromLocation, String toLocation);
}
