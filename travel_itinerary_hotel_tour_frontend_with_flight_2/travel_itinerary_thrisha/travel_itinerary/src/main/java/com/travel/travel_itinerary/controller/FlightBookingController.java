package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.model.FlightBooking;
import com.travel.travel_itinerary.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controller to handle flight booking operations.
 */
@Controller
@RequestMapping("/book-flight")
public class FlightBookingController {

    private final FlightService flightService;

    @Autowired
    public FlightBookingController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Display the flight booking form.
     */
    @GetMapping("{flightId}")
    public String showBookingForm(@PathVariable("flightId") Long flightId, Model model) {
        Flight flight = flightService.getFlightById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight ID: " + flightId));

        FlightBooking booking = new FlightBooking();
        booking.setFlight(flight);

        model.addAttribute("flight", flight);
        model.addAttribute("booking", booking);  // Make sure 'booking' is added to the model

        return "flight_booking_form";
    }

    /**
     * Handle form submission for booking a flight.
     */
    @PostMapping("/{flightId}")
    public String bookFlight(@PathVariable("flightId") Long flightId,
                             @ModelAttribute("booking") FlightBooking booking,
                             Model model) {

        Flight flight = flightService.getFlightById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight ID: " + flightId));

        // Set the flight and calculate total price
        booking.setFlight(flight);
        booking.setFromLocation(flight.getFromLocation());
        booking.setToLocation(flight.getToLocation());
        double total = flight.getPrice() * booking.getNumberOfPassengers();
        booking.setTotalPrice(total);

        // Save the booking
        FlightBooking confirmedBooking = flightService.bookFlight(flightId, booking);

        // Add attributes to model for Thymeleaf rendering
        model.addAttribute("booking", confirmedBooking);
        model.addAttribute("flight", flight);

        return "flight_booking_confirmation";  // Return the view name
    }
}
