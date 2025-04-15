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
        TourPackage tourPackage = tourPackageService.getTourPackageById(tourId);

        if (tourPackage == null) {
            model.addAttribute("errorMessage", "Invalid tour package ID");
            return "error"; // Display an error page (create error.html)
        }

        TourBooking tourBooking = new TourBooking();
        tourBooking.setTourPackage(tourPackage); // Pre-fill selected tour

        model.addAttribute("tourBooking", tourBooking);
        model.addAttribute("tourPackage", tourPackage);
        return "bookingForm"; // Show booking form (bookingForm.html)
    }

    /**
     * Process the booking form submission
     */
    @PostMapping("/book")
    public String processBooking(@ModelAttribute("tourBooking") TourBooking tourBooking, Model model) {
        Long packageId = tourBooking.getTourPackage().getId();
        TourPackage selectedPackage = tourPackageService.getTourPackageById(packageId);

        if (selectedPackage == null) {
            model.addAttribute("errorMessage", "Selected tour package not found");
            return "error";
        }

        if (selectedPackage.getAvailableSpots() <= 0) {
            model.addAttribute("errorMessage", "No spots available for this tour");
            return "error";
        }

        // Set booking date to today's date
        tourBooking.setBookingDate(LocalDate.now());

        // Update available spots
        selectedPackage.setAvailableSpots(selectedPackage.getAvailableSpots() - tourBooking.getNumberOfPeople());
        tourPackageService.updateTourPackage(selectedPackage);

        // Save the booking
        tourBookingService.createBooking(tourBooking);

        model.addAttribute("tourBooking", tourBooking);
        return "bookingConfirmation"; // Show confirmation (bookingConfirmation.html)
    }

    /**
     * View all tour bookings (for admin or customer history)
     */
    @GetMapping("/all")
    public String viewAllBookings(Model model) {
        model.addAttribute("tourBookings", tourBookingService.getAllBookings());
        return "bookingList"; // Show all bookings (bookingList.html)
    }
}
