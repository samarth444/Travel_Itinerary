package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Custom query to find all bookings by a user's ID
    List<Booking> findByUserId(Long userId);
}
