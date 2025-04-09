package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Booking;
import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.model.User;
import com.travel.travel_itinerary.repository.UserRepository;
import com.travel.travel_itinerary.service.BookingService;
import com.travel.travel_itinerary.service.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/booking/confirm")
    public String confirmBooking(@RequestParam("flightId") Long flightId,
                                 @RequestParam("userId") Long userId,
                                 @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                 Model model) {

        Optional<Flight> optionalFlight = flightService.findFlightById(flightId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalFlight.isEmpty()) {
            model.addAttribute("message", "Booking failed. Flight not found.");
            return "booking-confirmation";
        }

        if (optionalUser.isEmpty()) {
            model.addAttribute("message", "Booking failed. User not found.");
            return "booking-confirmation";
        }

        Flight flight = optionalFlight.get();
        User user = optionalUser.get();

        // Save booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setDate(date);
        booking.setDestination(flight.getDestination());

        Booking savedBooking = bookingService.saveBooking(booking);

        // Pass data to view
        model.addAttribute("flight", flight);
        model.addAttribute("booking", savedBooking);
        model.addAttribute("message", "Booking Confirmed!");

        return "booking-confirmation";
    }
}
