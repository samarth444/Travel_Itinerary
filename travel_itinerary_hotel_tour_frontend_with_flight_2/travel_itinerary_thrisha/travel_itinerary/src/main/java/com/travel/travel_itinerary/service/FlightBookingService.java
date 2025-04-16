package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.FlightBooking;
import com.travel.travel_itinerary.repository.FlightBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightBookingService {

    @Autowired
    private FlightBookingRepository bookingRepository;

    public FlightBooking saveBooking(FlightBooking booking) {
        return bookingRepository.save(booking);
    }

    public List<FlightBooking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public FlightBooking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
}
