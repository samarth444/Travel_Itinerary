package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.TourBooking;
import com.travel.travel_itinerary.model.TourPackage;
import com.travel.travel_itinerary.service.TourBookingService;
import com.travel.travel_itinerary.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/tourbookings")
public class TourBookingController {

    @Autowired
    private TourPackageService tourPackageService;

    @Autowired
    private TourBookingService tourBookingService;

    /**
     * Show booking form for a selected tour
     */
    @GetMapping("/book")
    public String showBookingForm(@RequestParam("tourId") Long tourId, Model model) {
        // Get the tour package by ID
        TourPackage tourPackage = tourPackageService.getTourPackageById(tourId);

        // If the tour package doesn't exist, show an error page
        if (tourPackage == null) {
            model.addAttribute("errorMessage", "Invalid tour package ID");
            return "error"; // Create error.html to display error message
        }

        // Create a new booking object and pre-fill the tour package data
        TourBooking tourBooking = new TourBooking();
        tourBooking.setTourPackage(tourPackage); // Set the selected tour package

        // Add data to the model for the view
        model.addAttribute("tourBooking", tourBooking);
        model.addAttribute("tourPackage", tourPackage);
        return "bookingForm"; // Return the booking form page (bookingForm.html)
    }

    /**
     * Process the booking form submission
     */
    @PostMapping("/book")
    public String processBooking(@ModelAttribute("tourBooking") TourBooking tourBooking, Model model) {
        // Get the tour package ID from the booking
        Long packageId = tourBooking.getTourPackage().getId();
        // Get the selected tour package
        TourPackage selectedPackage = tourPackageService.getTourPackageById(packageId);

        // If the selected package doesn't exist, show an error page
        if (selectedPackage == null) {
            model.addAttribute("errorMessage", "Selected tour package not found");
            return "error"; // Create error.html to display error message
        }

        // Check if there are enough available spots for the booking
        if (selectedPackage.getAvailableSpots() <= 0) {
            model.addAttribute("errorMessage", "No spots available for this tour");
            return "error"; // Create error.html to display error message
        }

        // Set booking date to today's date
        tourBooking.setBookingDate(LocalDate.now());

        // ✅ Calculate total amount
        double totalAmount = selectedPackage.getPrice() * tourBooking.getNumberOfPeople();
        tourBooking.setTotalAmount(totalAmount);

        // Update the available spots for the selected tour package
        selectedPackage.setAvailableSpots(selectedPackage.getAvailableSpots() - tourBooking.getNumberOfPeople());
        tourPackageService.updateTourPackage(selectedPackage);

        // Save the booking to the database
        tourBookingService.createBooking(tourBooking);

        // Add the tour booking data to the model for confirmation page
        model.addAttribute("tourBooking", tourBooking);
        return "bookingConfirmation"; // Return the booking confirmation page (bookingConfirmation.html)
    }

    /**
     * View all tour bookings (for admin or customer history)
     */
    @GetMapping("/all")
    public String viewAllBookings(Model model) {
        // Get all the bookings and add them to the model
        model.addAttribute("tourBookings", tourBookingService.getAllBookings());
        return "bookingList"; // Return the booking list page (bookingList.html)
    }
}
