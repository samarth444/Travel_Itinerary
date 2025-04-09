package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Itinerary;
import com.travel.travel_itinerary.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    // Get all itineraries
    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    // Get a specific itinerary by ID
    public Itinerary getItineraryById(Long id) {
        Optional<Itinerary> itinerary = itineraryRepository.findById(id);
        return itinerary.orElseThrow(() -> new RuntimeException("Itinerary not found"));
    }

    // Create a new itinerary
    public Itinerary createItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    // Update an existing itinerary
    public Itinerary updateItinerary(Long id, Itinerary updatedItinerary) {
        Itinerary existingItinerary = getItineraryById(id);

        if (updatedItinerary.getDestination() != null) {
            existingItinerary.setDestination(updatedItinerary.getDestination());
        }
        if (updatedItinerary.getStartDate() != null) {
            existingItinerary.setStartDate(updatedItinerary.getStartDate());
        }
        if (updatedItinerary.getEndDate() != null) {
            existingItinerary.setEndDate(updatedItinerary.getEndDate());
        }
        if (updatedItinerary.getActivities() != null) {
            existingItinerary.setActivities(updatedItinerary.getActivities());
        }

        return itineraryRepository.save(existingItinerary);
    }

    // Delete an itinerary
    public void deleteItinerary(Long id) {
        itineraryRepository.deleteById(id);
    }
}
