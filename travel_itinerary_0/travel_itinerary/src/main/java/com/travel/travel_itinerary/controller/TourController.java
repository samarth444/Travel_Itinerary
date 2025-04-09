package com.travel.travel_itinerary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TourController {

    @GetMapping("/tours")
    public String tourPage(Model model) {
        model.addAttribute("pageTitle", "Tour Packages");
        return "tours"; // Renders tours.html
    }
}
