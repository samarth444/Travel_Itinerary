package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    // ✅ NEW: Paginated search by source, destination, and date
    public Page<Flight> findFlightsPaginated(String from, String to, LocalDate date, Pageable pageable) {
        return flightRepository.findBySourceIgnoreCaseAndDestinationIgnoreCaseAndFlightDate(
                from.trim(), to.trim(), date, pageable
        );
    }

    // ✅ Optional: For keyword-based search (if implemented in repository)
    public Page<Flight> searchFlightsByKeyword(String keyword, Pageable pageable) {
        // You can implement a custom repository method if needed.
        return Page.empty(); // Placeholder
    }
}
