package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.dto.BookingRequestDTO;
import com.travel.travel_itinerary.model.Booking;
import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.model.User;
import com.travel.travel_itinerary.repository.BookingRepository;
import com.travel.travel_itinerary.repository.FlightRepository;
import com.travel.travel_itinerary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    // Book a flight
    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequestDTO bookingRequest) {
        Optional<User> userOpt = userRepository.findById(bookingRequest.getUserId());
        Optional<Flight> flightOpt = flightRepository.findById(bookingRequest.getFlightId());

        if (userOpt.isEmpty() || flightOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid user or flight ID");
        }

        Booking booking = new Booking(
                userOpt.get(),
                flightOpt.get(),
                bookingRequest.getBookingDate(),
                bookingRequest.getNumberOfSeats()
        );

        Booking savedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(savedBooking);
    }

    // Optional: Get all bookings for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingsForUser(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(bookingRepository.findByUser(userOpt.get()));
    }
}
