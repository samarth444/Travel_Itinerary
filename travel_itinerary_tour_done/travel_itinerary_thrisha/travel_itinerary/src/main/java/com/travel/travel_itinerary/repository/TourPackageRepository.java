package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {

    // Custom query methods can be added here if needed.
    // For example, finding a tour package by name:
    // Optional<TourPackage> findByName(String name);
}
