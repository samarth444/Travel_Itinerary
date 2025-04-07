package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Booking;
import com.travel.travel_itinerary.model.Itinerary;
import com.travel.travel_itinerary.model.User;
import com.travel.travel_itinerary.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get a specific booking by ID
    public Booking getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // Create a new booking
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Update an existing booking
    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking existingBooking = getBookingById(id);

        if (updatedBooking.getUser() != null) {
            existingBooking.setUser(updatedBooking.getUser());
        }
        if (updatedBooking.getItinerary() != null) {
            existingBooking.setItinerary(updatedBooking.getItinerary());
        }
        if (updatedBooking.getBookingDate() != null) {
            existingBooking.setBookingDate(updatedBooking.getBookingDate());
        }
        if (updatedBooking.getStatus() != null) {
            existingBooking.setStatus(updatedBooking.getStatus());
        }

        return bookingRepository.save(existingBooking);
    }

    // Delete a booking
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
