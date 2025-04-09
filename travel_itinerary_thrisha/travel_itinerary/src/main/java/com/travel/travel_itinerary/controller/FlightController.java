package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightService flightService;

    // Display flight search form
    @GetMapping("/book")
    public String showSearchForm() {
        logger.info("Navigating to flight search page.");
        return "flight_search"; // Ensure flight_search.html exists
    }

    // Handle search request
    @PostMapping("/search")
    public String searchFlights(@RequestParam String from, @RequestParam String to, Model model) {
        logger.info("Searching flights from '{}' to '{}'", from.trim(), to.trim());
        List<Flight> flights = flightService.findFlights(from, to);

        if (flights.isEmpty()) {
            model.addAttribute("error", "No flights found!");
        } else {
            model.addAttribute("flights", flights);
        }

        model.addAttribute("userId", 1L); // Temporary static userId
        return "flights_results";
    }


    // Find flight by flight number
    @GetMapping("/find/{flightNumber}")
    public String findFlightByNumber(@PathVariable String flightNumber, Model model) {
        logger.info("Searching flight with flightNumber: {}", flightNumber);
        Optional<Flight> flight = flightService.findFlightByNumber(flightNumber);

        if (flight.isPresent()) {
            model.addAttribute("flight", flight.get());
            return "flight_details"; // Or use your updated book.html
        } else {
            logger.warn("Flight with number {} not found!", flightNumber);
            model.addAttribute("error", "Flight not found!");
            return "error_page";
        }
    }

    // ✅ POST endpoint to handle booking confirmation
    @PostMapping("/confirm")
    public String confirmBooking(@RequestParam String flightNumber,
                                 @RequestParam Long userId,
                                 Model model) {
        logger.info("Confirmed booking for flight: {}, by user: {}", flightNumber, userId);

        Optional<Flight> flight = flightService.findFlightByNumber(flightNumber);
        if (flight.isPresent()) {
            model.addAttribute("message", "Booking confirmed for flight " + flightNumber);
            model.addAttribute("flight", flight.get());
            model.addAttribute("userId", userId); // pass it back if needed
        } else {
            model.addAttribute("message", "Flight not found for booking.");
        }

        return "confirmation";
    }

}
