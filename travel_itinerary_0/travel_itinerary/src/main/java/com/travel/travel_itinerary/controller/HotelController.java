package com.travel.travel_itinerary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {

    @GetMapping("/hotels")
    public String hotelPage(Model model) {
        model.addAttribute("pageTitle", "Hotel Booking");
        return "hotels"; // Renders hotels.html
    }
}
