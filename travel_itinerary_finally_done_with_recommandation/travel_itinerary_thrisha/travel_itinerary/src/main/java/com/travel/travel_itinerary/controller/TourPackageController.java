package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.TourPackage;
import com.travel.travel_itinerary.model.Itinerary;
import com.travel.travel_itinerary.service.TourPackageService;
import com.travel.travel_itinerary.service.ItineraryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tourpackages")
public class TourPackageController {

    @Autowired
    private TourPackageService tourPackageService;

    @Autowired
    private ItineraryService itineraryService;

    // Display all tour packages
    @GetMapping
    public String getAllTourPackages(Model model) {
        model.addAttribute("tourPackages", tourPackageService.getAllTourPackages());
        return "tourpackage_list";
    }

    // Add a new tour package
    @GetMapping("/add")
    public String showAddTourPackageForm(Model model) {
        model.addAttribute("tourPackage", new TourPackage());
        return "tourpackage_add";
    }

    @PostMapping("/add")
    public String addTourPackage(@ModelAttribute TourPackage tourPackage) {
        tourPackageService.addTourPackage(tourPackage);
        return "redirect:/tourpackages";
    }

    // View a specific tour package by ID
    @GetMapping("/{id}")
    public String viewTourPackage(@PathVariable Long id, Model model) {
        TourPackage tourPackage = tourPackageService.getTourPackageById(id);
        if (tourPackage != null) {
            model.addAttribute("tourPackage", tourPackage);
            return "tourpackage_detail";
        }
        model.addAttribute("error", "Tour package not found!");
        return "error_page";
    }

    // View itinerary by tour package
    @GetMapping("/{id}/itinerary")
    public String viewItinerary(@PathVariable Long id, Model model) {
        TourPackage tourPackage = tourPackageService.getTourPackageById(id);
        List<Itinerary> itineraries = itineraryService.getItineraryByTourPackageId(id);

        model.addAttribute("tourPackage", tourPackage);
        model.addAttribute("itineraries", itineraries);
        return "itinerary";
    }

    // Show booking form for a specific tour package
    @GetMapping("/{id}/book")
    public String showBookingForm(@PathVariable Long id, Model model) {
        TourPackage tourPackage = tourPackageService.getTourPackageById(id);
        if (tourPackage != null) {
            model.addAttribute("tourPackage", tourPackage);
            return "booking_form"; // Thymeleaf template for booking form
        }
        model.addAttribute("error", "Tour package not found!");
        return "error_page";
    }

    @GetMapping("/location/{location}")
    public String getTourPackagesByLocation(@PathVariable String location, Model model) {
        List<TourPackage> tourPackages = tourPackageService.getPackagesByLocation(location);
        model.addAttribute("tourPackages", tourPackages); // ✔ match with `tours.html`
        model.addAttribute("pageTitle", "Tour Packages in " + location);
        return "tours"; // ✔ match with your Thymeleaf view
    }

    // (Optional: If you're submitting the form and saving the booking, you'd add a POST method here)
}
