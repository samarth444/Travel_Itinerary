package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findByDepartureAndDestination(String departure, String destination);

    Optional<Flight> findByFlightNumber(String flightNumber);
}
