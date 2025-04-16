package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

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

    // ✅ Handle search request with date and pagination
    @PostMapping("/search")
    public String searchFlights(@RequestParam String from,
                                @RequestParam String to,
                                @RequestParam String date,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                Model model) {
        logger.info("Searching flights from '{}' to '{}' on date '{}'", from, to, date);

        try {
            LocalDate flightDate = LocalDate.parse(date); // expects yyyy-MM-dd
            Page<Flight> paginatedFlights = flightService.findFlightsPaginated(from, to, flightDate, PageRequest.of(page, size));

            if (paginatedFlights.isEmpty()) {
                model.addAttribute("error", "No flights found on " + date);
            } else {
                model.addAttribute("flights", paginatedFlights.getContent());
                model.addAttribute("totalPages", paginatedFlights.getTotalPages());
                model.addAttribute("currentPage", page);
            }

            model.addAttribute("userId", 1L); // Static user for now
            return "flights_results";

        } catch (DateTimeParseException e) {
            logger.error("Invalid date format: {}", date);
            model.addAttribute("error", "Invalid date format! Please use yyyy-MM-dd.");
            return "flight_search";
        }
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

    // POST endpoint to handle booking confirmation
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
