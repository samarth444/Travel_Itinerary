package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * ✅ Displays the flight search page
     */
    @GetMapping
    public String flightPage(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("pageTitle", "Flight Booking");

        if (error != null) {
            model.addAttribute("error", error);
        }

        return "flight_search"; // Matches your Thymeleaf template
    }

    /**
     * ✅ Searches and displays available flights
     */
    @GetMapping("/results")
    public String showFlightResults(@RequestParam(required = false) String from,
                                    @RequestParam(required = false) String to,
                                    @RequestParam(required = false) String date,
                                    Model model) {
        if (from == null || to == null || date == null || from.isBlank() || to.isBlank() || date.isBlank()) {
            model.addAttribute("error", "All fields are required.");
            return "flight_search";
        }

        List<Flight> flights = flightService.getFlights(from, to, date);

        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("date", date);
        model.addAttribute("flights", flights);

        if (flights.isEmpty()) {
            model.addAttribute("error", "No flights found for the selected route.");
        }

        return "flight_results"; // Matches your Thymeleaf template
    }

    /**
     * ✅ Fetches flight details by flight number
     */
    @GetMapping("/details/{flightNumber}")
    public String flightDetails(@PathVariable String flightNumber, Model model) {
        Flight flight = flightService.getFlightByNumber(flightNumber);

        if (flight == null) {
            model.addAttribute("error", "Flight not found.");
            return "flight_results";
        }

        model.addAttribute("flight", flight);
        return "flight_details"; // Matches your Thymeleaf template
    }
}
