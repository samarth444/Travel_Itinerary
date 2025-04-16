package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFromLocationAndToLocationAndDepartureDate(String fromLocation, String toLocation, LocalDate departureDate);
}
