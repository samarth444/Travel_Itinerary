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
            return "error";
        }

        TourBooking tourBooking = new TourBooking();
        tourBooking.setTourPackage(tourPackage);

        model.addAttribute("tourBooking", tourBooking);
        model.addAttribute("tourPackage", tourPackage);
        return "bookingForm";
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

        if (selectedPackage.getAvailableSpots() < tourBooking.getNumberOfPeople()) {
            model.addAttribute("errorMessage", "Not enough spots available for this tour");
            return "error";
        }

        // Set booking date and update available spots
        tourBooking.setBookingDate(LocalDate.now());
        selectedPackage.setAvailableSpots(selectedPackage.getAvailableSpots() - tourBooking.getNumberOfPeople());
        tourPackageService.updateTourPackage(selectedPackage);

        // Save booking and get the saved entity (assuming ID is generated)
        TourBooking savedBooking = tourBookingService.createBooking(tourBooking);

        // Redirect to confirmation with bookingId
        return "redirect:/tourbookings/confirmation?bookingId=" + savedBooking.getId();
    }

    /**
     * Booking confirmation view (GET after POST)
     */
    @GetMapping("/confirmation")
    public String showBookingConfirmation(@RequestParam("bookingId") Long bookingId, Model model) {
        TourBooking booking = tourBookingService.getBookingById(bookingId);
        if (booking == null) {
            model.addAttribute("errorMessage", "Booking not found.");
            return "error";
        }

        model.addAttribute("tourBooking", booking);
        model.addAttribute("tourPackage", booking.getTourPackage());
        return "bookingConfirmation";
    }

    /**
     * View all bookings
     */
    @GetMapping("/all")
    public String viewAllBookings(Model model) {
        model.addAttribute("tourBookings", tourBookingService.getAllBookings());
        return "bookingList";
    }
}
