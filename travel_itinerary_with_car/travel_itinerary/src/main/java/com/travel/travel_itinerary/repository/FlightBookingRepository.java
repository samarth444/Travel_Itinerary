package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
}
