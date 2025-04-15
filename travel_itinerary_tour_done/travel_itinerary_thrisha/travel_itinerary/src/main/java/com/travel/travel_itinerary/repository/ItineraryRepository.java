package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    List<Itinerary> findByTourPackageId(Long tourPackageId);
    List<Itinerary> findByTourPackageIdOrderByDayNumberAsc(Long tourPackageId);
}

