package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.TourBooking;
import com.travel.travel_itinerary.repository.TourBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TourBookingService {

    @Autowired
    private TourBookingRepository tourBookingRepository;

    // Method to retrieve all bookings
    public List<TourBooking> getAllBookings() {
        return tourBookingRepository.findAll();
    }


    public TourBooking createBooking(TourBooking tourBooking) {
        // Save the booking information to the database
        return tourBookingRepository.save(tourBooking);
    }
}
