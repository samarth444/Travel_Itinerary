package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    // Corrected method to find itineraries by user ID
    List<Itinerary> findByUser_Id(Long userId);
}
