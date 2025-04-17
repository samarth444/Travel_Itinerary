package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {
    List<TourPackage> findByLocationIgnoreCase(String location);

    // Custom query methods can be added here if needed.
    // For example, finding a tour package by name:
    // Optional<TourPackage> findByName(String name);
}

