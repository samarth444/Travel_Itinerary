package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.TourPackage;
import com.travel.travel_itinerary.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TourController {

    @Autowired
    private TourPackageService tourPackageService;

    @GetMapping("/tours")
    public String tourPage(@RequestParam(defaultValue = "1") int page, Model model) {
        // Define the number of tour packages per page
        int pageSize = 6;  // You can change this to display more or fewer tours per page

        // Fetch the paginated list of tour packages from the service
        Pageable pageable = PageRequest.of(page - 1, pageSize);  // Convert page to 0-based index
        Page<TourPackage> tourPage = tourPackageService.getAllTourPackages(pageable);

        // Add attributes to the model
        model.addAttribute("tourPackages", tourPage.getContent());  // The list of tour packages on the current page
        model.addAttribute("currentPage", page);  // The current page number
        model.addAttribute("totalPages", tourPage.getTotalPages());  // Total number of pages
        model.addAttribute("pageTitle", "Tour Packages");

        return "tours"; // This will render the 'tours.html' Thymeleaf template
    }
}
