package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.model.FlightBooking;
import com.travel.travel_itinerary.service.FlightBookingService;
import com.travel.travel_itinerary.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling flight-related operations.
 */
@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightBookingService flightBookingService;

    /**
     * Lists all available flights for the user to select.
     *
     * @param model Spring Model to pass data to the view
     * @return Thymeleaf template name for listing flights
     */
    @GetMapping
    public String listFlights(Model model) {
        // Fetch all available flights from the service
        model.addAttribute("flights", flightService.getAllFlights());
        // Return the view name for flight listing
        return "flight_list";  // Create a template flight_list.html to show available flights
    }

    /**
     * Displays the booking form for a specific flight.
     *
     * @param id    ID of the flight to book
     * @param model Spring Model to pass data to the view
     * @return Thymeleaf template name for booking form
     */
    @GetMapping("/{id}/book")
    public String showBookingForm(@PathVariable Long id, Model model) {
        // Fetch the flight by ID from the service, handle Optional properly
        Flight flight = flightService.getFlightById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight ID: " + id));

        // Add flight and booking form to the model
        model.addAttribute("flight", flight);
        model.addAttribute("booking", new FlightBooking());

        // Return the view for booking form
        return "flight_booking_form";  // Create flight_booking_form.html for user to enter booking details
    }

    /**
     * Processes the flight booking form submission.
     *
     * @param flightId ID of the flight to book
     * @param booking FlightBooking object bound from form
     * @param model   Spring Model to pass data to the view
     * @return Thymeleaf template name for booking confirmation
     */
    @PostMapping("/{flightId}/book")
    public String processBooking(@PathVariable Long flightId,
                                 @ModelAttribute("booking") FlightBooking booking,
                                 Model model) {

        // Fetch the flight by ID from the service, handle Optional properly
        Flight flight = flightService.getFlightById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight ID: " + flightId));

        // Link flight to booking
        booking.setFlight(flight);

        // Calculate and set total price
        double totalPrice = flight.getPrice() * booking.getNumberOfPassengers();
        booking.setTotalPrice(totalPrice);

        // Save the booking using the flightBookingService
        FlightBooking savedBooking = flightBookingService.saveBooking(booking);

        // Add saved booking to the model for display on the confirmation page
        model.addAttribute("booking", savedBooking);

        // Return the confirmation page view
        return "flight_booking_confirmation";  // Create a flight_booking_confirmation.html to display the confirmation
    }
}
