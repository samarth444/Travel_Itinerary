package com.travel.travel_itinerary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    @GetMapping("/cars")
    public String carPage(Model model) {
        model.addAttribute("pageTitle", "Car Rentals");
        return "cars"; // Renders cars.html
    }
}
