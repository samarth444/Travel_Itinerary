package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Itinerary;

import java.util.List;

public interface ItineraryService {
    List<Itinerary> getAllItineraries();
    Itinerary getItineraryById(Long id);
    Itinerary saveItinerary(Itinerary itinerary);
    void deleteItinerary(Long id);
    List<Itinerary> getItineraryByTourPackageId(Long tourPackageId);

}
