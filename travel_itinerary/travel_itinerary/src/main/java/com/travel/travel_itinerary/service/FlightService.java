package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Retrieves flights based on departure, destination, and date.
     */
    public List<Flight> getFlights(String from, String to, String date) {
        return flightRepository.findByDepartureAndDestination(from, to);
    }

    /**
     * Retrieves flight details based on flight number.
     */
    public Flight getFlightByNumber(String flightNumber) {
        Optional<Flight> flight = flightRepository.findByFlightNumber(flightNumber);
        return flight.orElse(null);
    }
}
