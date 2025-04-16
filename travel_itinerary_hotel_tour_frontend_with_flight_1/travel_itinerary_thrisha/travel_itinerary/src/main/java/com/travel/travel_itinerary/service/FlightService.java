package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.model.FlightBooking;
import com.travel.travel_itinerary.repository.FlightBookingRepository;
import com.travel.travel_itinerary.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightBookingRepository flightBookingRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, FlightBookingRepository flightBookingRepository) {
        this.flightRepository = flightRepository;
        this.flightBookingRepository = flightBookingRepository;
    }

    // Get all flights from the database
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Get flight by its ID
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    // Book a flight for the user
    public FlightBooking bookFlight(Long flightId, FlightBooking booking) {
        Optional<Flight> flightOpt = flightRepository.findById(flightId);

        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            booking.setFlight(flight); // Set the flight for the booking
            booking.calculateTotalPrice(); // Calculate the total price for the booking
            return flightBookingRepository.save(booking); // Save the booking in the database
        }

        throw new IllegalArgumentException("Flight not found with id: " + flightId); // Throw an exception if the flight is not found
    }
}
