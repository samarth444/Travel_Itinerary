package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // Search by partial location match (case-insensitive)
    List<Hotel> findByLocationContainingIgnoreCase(String location);

    // ❌ Removed invalid method with checkInDate and checkOutDate,
    // since those fields are not in the Hotel entity.
}
