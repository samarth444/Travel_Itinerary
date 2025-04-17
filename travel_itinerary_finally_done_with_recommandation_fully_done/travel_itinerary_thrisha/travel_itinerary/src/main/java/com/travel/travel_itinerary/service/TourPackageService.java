package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Itinerary;
import com.travel.travel_itinerary.model.TourPackage;
import com.travel.travel_itinerary.repository.TourPackageRepository;
import com.travel.travel_itinerary.repository.ItineraryRepository;  // ✅ Add this import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourPackageService {

    @Autowired
    private TourPackageRepository tourPackageRepository;

    @Autowired
    private ItineraryRepository itineraryRepository;  // ✅ Inject itineraryRepository

    // Get all tour packages (without pagination)
    public List<TourPackage> getAllTourPackages() {
        return tourPackageRepository.findAll();
    }

    // Get all tour packages with pagination
    public Page<TourPackage> getAllTourPackages(Pageable pageable) {
        return tourPackageRepository.findAll(pageable);
    }

    // Get a tour package by its ID
    public TourPackage getTourPackageById(Long id) {
        return tourPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TourPackage not found with ID: " + id));
    }

    // Add a new tour package
    public TourPackage addTourPackage(TourPackage tourPackage) {
        return tourPackageRepository.save(tourPackage);
    }

    // Update an existing tour package (if needed)
    public TourPackage updateTourPackage(Long id, TourPackage updatedTourPackage) {
        TourPackage existingTourPackage = getTourPackageById(id);
        existingTourPackage.setName(updatedTourPackage.getName());
        existingTourPackage.setDescription(updatedTourPackage.getDescription());
        existingTourPackage.setPrice(updatedTourPackage.getPrice());
        existingTourPackage.setDuration(updatedTourPackage.getDuration());
        existingTourPackage.setLocation(updatedTourPackage.getLocation());
        existingTourPackage.setAvailableSpots(updatedTourPackage.getAvailableSpots());
        existingTourPackage.setAvailabilityCalendar(updatedTourPackage.getAvailabilityCalendar());
        existingTourPackage.setImageUrl(updatedTourPackage.getImageUrl());
        return tourPackageRepository.save(existingTourPackage);
    }

    // Delete a tour package by its ID
    public void deleteTourPackage(Long id) {
        TourPackage tourPackage = getTourPackageById(id);
        tourPackageRepository.delete(tourPackage);
    }

    // Update an existing tour package (generic method for saving)
    public void updateTourPackage(TourPackage tourPackage) {
        tourPackageRepository.save(tourPackage);
    }

    // Get the itinerary for a tour package
    public List<Itinerary> getItineraryForTourPackage(Long tourId) {
        return itineraryRepository.findByTourPackageIdOrderByDayNumberAsc(tourId);
    }

    public List<TourPackage> getPackagesByLocation(String location) {
        return tourPackageRepository.findByLocationIgnoreCase(location);
    }



}
