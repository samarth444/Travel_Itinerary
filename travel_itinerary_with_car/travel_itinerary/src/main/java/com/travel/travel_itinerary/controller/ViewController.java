package com.travel.travel_itinerary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/booking/confirm")  // This URL must match what is being requested
    public String confirmation() {
        return "confirmation"; // Should match the filename without .html
    }
}
