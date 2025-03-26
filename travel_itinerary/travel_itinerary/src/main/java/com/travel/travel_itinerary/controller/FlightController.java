package com.travel.travel_itinerary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {

    @GetMapping("/flights")
    public String flightPage(Model model) {
        model.addAttribute("pageTitle", "Flight Booking");
        return "flights"; // Renders flights.html
    }

    @GetMapping("/flights/search")
    public String showFlightSearchPage() {
        return "flight_search"; // Renders flight_search.html
    }

    @GetMapping("/flights/results")
    public String showFlightResults(@RequestParam String from,
                                    @RequestParam String to,
                                    @RequestParam String date,
                                    Model model) {
        // Simulated flight results (replace with actual service logic)
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("date", date);
        model.addAttribute("flights", new String[]{"Flight A - 10:00 AM", "Flight B - 2:30 PM", "Flight C - 7:00 PM"});

        return "flight_results"; // Renders flight_results.html
    }
}
