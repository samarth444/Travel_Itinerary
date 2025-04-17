package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.CarBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBookingRepository extends JpaRepository<CarBooking, Long> {
    // You can add custom query methods here if needed
}