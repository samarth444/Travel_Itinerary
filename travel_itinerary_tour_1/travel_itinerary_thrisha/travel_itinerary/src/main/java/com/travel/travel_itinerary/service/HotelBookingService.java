package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.HotelBooking;
import com.travel.travel_itinerary.repository.HotelBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelBookingService {

    @Autowired
    private HotelBookingRepository hotelBookingRepository;

    public HotelBooking saveBooking(HotelBooking booking) {
        return hotelBookingRepository.save(booking);
    }
}
