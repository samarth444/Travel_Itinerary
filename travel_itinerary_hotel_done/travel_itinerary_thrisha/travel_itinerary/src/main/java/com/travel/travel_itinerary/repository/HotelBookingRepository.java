package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {
}
