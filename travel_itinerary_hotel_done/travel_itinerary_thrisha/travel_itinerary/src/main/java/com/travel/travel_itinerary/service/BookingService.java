package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Booking;
import com.travel.travel_itinerary.model.Flight;
import com.travel.travel_itinerary.model.User;
import com.travel.travel_itinerary.repository.BookingRepository;
import com.travel.travel_itinerary.repository.FlightRepository;
import com.travel.travel_itinerary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    /**
     * Retrieve all bookings.
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Retrieve a booking by ID.
     */
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    /**
     * Save a new booking.
     */
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    /**
     * Update an existing booking.
     */
    public Booking updateBooking(Long id, Booking updatedBooking) {
        return bookingRepository.findById(id).map(existingBooking -> {
            existingBooking.setUser(updatedBooking.getUser());
            existingBooking.setFlight(updatedBooking.getFlight());
            existingBooking.setBookingDate(updatedBooking.getBookingDate());
            existingBooking.setNumberOfSeats(updatedBooking.getNumberOfSeats());
            return bookingRepository.save(existingBooking);
        }).orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    /**
     * Delete a booking by ID.
     */
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete: Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }

    /**
     * Retrieve all bookings for a specific user.
     */
    public List<Booking> getBookingsByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return bookingRepository.findByUser(user.get());
    }

    /**
     * Validate and create a booking using userId and flightId.
     */
    public Booking createBooking(Long userId, Long flightId, int seats, java.time.LocalDate bookingDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + flightId));

        Booking booking = new Booking(user, flight, bookingDate, seats);
        return bookingRepository.save(booking);
    }
}
