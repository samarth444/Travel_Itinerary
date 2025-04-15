package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Itinerary;
import com.travel.travel_itinerary.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/itineraries")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    // Display all itineraries
    @GetMapping
    public String getAllItineraries(Model model) {
        List<Itinerary> itineraries = itineraryService.getAllItineraries();
        model.addAttribute("itineraries", itineraries);
        return "itinerary_list";  // Make sure this view (Thymeleaf template) exists
    }

    // Show a specific itinerary
    @GetMapping("/{id}")
    public String getItinerary(@PathVariable Long id, Model model) {
        Itinerary itinerary = itineraryService.getItineraryById(id);
        model.addAttribute("itinerary", itinerary);
        return "itinerary_detail";  // Make sure this view (Thymeleaf template) exists
    }

    // Add a new itinerary (show form)
    @GetMapping("/new")
    public String showItineraryForm(Model model) {
        model.addAttribute("itinerary", new Itinerary());
        return "itinerary_form";  // Make sure this view (Thymeleaf template) exists
    }

    // Save new or updated itinerary
    @PostMapping
    public String saveItinerary(@ModelAttribute Itinerary itinerary) {
        itineraryService.saveItinerary(itinerary); // Save the itinerary (new or updated)
        return "redirect:/itineraries";  // Redirect to the list of itineraries after saving
    }

    // Show the form to update an existing itinerary
    @GetMapping("/edit/{id}")
    public String showEditItineraryForm(@PathVariable Long id, Model model) {
        Itinerary itinerary = itineraryService.getItineraryById(id);  // Fetch the itinerary by ID
        model.addAttribute("itinerary", itinerary);
        return "itinerary_form";  // Use the same form for both adding and updating
    }

    // Delete an itinerary
    @GetMapping("/delete/{id}")
    public String deleteItinerary(@PathVariable Long id) {
        itineraryService.deleteItinerary(id);  // Delete the itinerary by ID
        return "redirect:/itineraries";  // Redirect to the list after deletion
    }
}
