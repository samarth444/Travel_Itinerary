package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.TourBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourBookingRepository extends JpaRepository<TourBooking, Long> {
}
