package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Itinerary;
import com.travel.travel_itinerary.model.TourPackage;
import com.travel.travel_itinerary.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TourController {

    @Autowired
    private TourPackageService tourPackageService;

    // Displays the paginated list of tour packages
    @GetMapping("/tours")
    public String tourPage(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 6;  // Number of tour packages per page
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Convert to 0-based index
        Page<TourPackage> tourPage = tourPackageService.getAllTourPackages(pageable);

        // Add attributes to the model to be displayed on the page
        model.addAttribute("tourPackages", tourPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", tourPage.getTotalPages());
        model.addAttribute("pageTitle", "Tour Packages");

        return "tours"; // Points to the 'tours.html' Thymeleaf template
    }

    // Displays the itinerary for a selected tour package
    @GetMapping("/tours/{tourId}/itinerary")
    public String viewItinerary(@PathVariable Long tourId, Model model) {
        // Fetch tour package and its itinerary details
        TourPackage tourPackage = tourPackageService.getTourPackageById(tourId);

        if (tourPackage == null) {
            model.addAttribute("errorMessage", "Tour Package not found");
            return "error"; // You can create an error.html template to handle the error page
        }

        List<Itinerary> itineraries = tourPackageService.getItineraryForTourPackage(tourId);

        // Add attributes to the model
        model.addAttribute("tourPackage", tourPackage);
        model.addAttribute("itineraries", itineraries);
        model.addAttribute("pageTitle", "Itinerary for " + tourPackage.getName());

        return "itinerary"; // Points to the 'itinerary.html' Thymeleaf template
    }
}
