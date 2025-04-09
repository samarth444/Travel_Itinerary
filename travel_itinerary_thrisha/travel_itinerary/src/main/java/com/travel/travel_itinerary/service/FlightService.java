package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> findFlights(String from, String to) {
        return flightRepository.findFlightsIgnoreCase(from.trim(), to.trim());
    }

    public Optional<Flight> findFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }

    public Optional<Flight> findFlightById(Long id) {
        return flightRepository.findById(id);
    }
}
