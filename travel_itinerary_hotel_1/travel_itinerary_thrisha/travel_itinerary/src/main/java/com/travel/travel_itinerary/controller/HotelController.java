package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Hotel;
import com.travel.travel_itinerary.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    /**
     * Loads the hotel search form page
     */
    @GetMapping("/book")
    public String showSearchForm() {
        logger.info("Navigating to hotel search form.");
        return "hotel_search";
    }

    /**
     * Handles hotel search based on location and date filters
     */
    @PostMapping("/search")
    public String searchHotels(@RequestParam String location,
                               @RequestParam String checkIn,
                               @RequestParam String checkOut,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "6") int size,
                               Model model) {
        logger.info("Searching hotels in '{}' from {} to {}", location, checkIn, checkOut);

        try {
            LocalDate checkInDate = LocalDate.parse(checkIn);
            LocalDate checkOutDate = LocalDate.parse(checkOut);

            Page<Hotel> paginatedHotels = hotelService.findHotelsPaginated(
                    location, checkInDate, checkOutDate, PageRequest.of(page, size));

            if (paginatedHotels.isEmpty()) {
                logger.warn("No hotels found for given criteria.");
                model.addAttribute("error", "No hotels found for your search.");
            } else {
                model.addAttribute("hotels", paginatedHotels.getContent());
                model.addAttribute("totalPages", paginatedHotels.getTotalPages());
                model.addAttribute("currentPage", page);
                model.addAttribute("hasNext", paginatedHotels.hasNext());
                model.addAttribute("hasPrevious", paginatedHotels.hasPrevious());
            }

            model.addAttribute("location", location);
            model.addAttribute("checkIn", checkIn);
            model.addAttribute("checkOut", checkOut);
            model.addAttribute("userId", 1L); // Static user ID for now

            return "hotel_results";

        } catch (DateTimeParseException e) {
            logger.error("Invalid date format: checkIn='{}', checkOut='{}'", checkIn, checkOut);
            model.addAttribute("error", "Invalid date format. Please use yyyy-MM-dd.");
            return "hotel_search";
        } catch (Exception ex) {
            logger.error("Unexpected error occurred during hotel search: {}", ex.getMessage());
            model.addAttribute("error", "An unexpected error occurred. Please try again later.");
            return "hotel_search";
        }
    }

    /**
     * View hotel details by ID
     */
    @GetMapping("/view/{id}")
    public String viewHotelById(@PathVariable Long id, Model model) {
        logger.info("Viewing hotel with ID: {}", id);

        Optional<Hotel> hotel = hotelService.findHotelById(id);
        if (hotel.isPresent()) {
            model.addAttribute("hotel", hotel.get());
            return "hotel_details";
        } else {
            logger.warn("Hotel with ID {} not found!", id);
            model.addAttribute("error", "Hotel not found.");
            return "error_page";
        }
    }

    /**
     * Booking form for a selected hotel
     */
    @GetMapping("/book/{id}")
    public String showBookingPage(@PathVariable Long id,
                                  @RequestParam Long userId,
                                  Model model) {
        logger.info("Opening booking page for hotel ID: {}", id);

        Optional<Hotel> hotel = hotelService.findHotelById(id);
        if (hotel.isPresent()) {
            model.addAttribute("hotel", hotel.get());
            model.addAttribute("userId", userId);
            return "book_hotel";
        } else {
            logger.error("Hotel with ID {} not found for booking.", id);
            model.addAttribute("error", "Hotel not found.");
            return "error_page";
        }
    }

    /**
     * Confirms a hotel booking
     */
    @PostMapping("/confirm")
    public String confirmBooking(@RequestParam Long hotelId,
                                 @RequestParam Long userId,
                                 Model model) {
        logger.info("Booking confirmed for hotel ID: {}, by user ID: {}", hotelId, userId);

        Optional<Hotel> hotel = hotelService.findHotelById(hotelId);
        if (hotel.isPresent()) {
            model.addAttribute("message", "Booking confirmed for hotel: " + hotel.get().getName());
            model.addAttribute("hotel", hotel.get());
            model.addAttribute("userId", userId);
        } else {
            logger.warn("Hotel not found during booking confirmation.");
            model.addAttribute("message", "Hotel not found for booking.");
        }

        return "hotel_confirmation";
    }
}
