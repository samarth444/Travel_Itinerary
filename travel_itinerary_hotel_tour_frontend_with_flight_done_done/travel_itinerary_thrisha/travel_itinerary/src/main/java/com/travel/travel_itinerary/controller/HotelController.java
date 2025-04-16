package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Hotel;
import com.travel.travel_itinerary.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.travel.travel_itinerary.model.HotelBooking;
import com.travel.travel_itinerary.service.HotelBookingService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelBookingService hotelBookingService;

    // 1. Display list of all hotels
    @GetMapping
    public String listHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "hotel_list"; // Renders hotel_list.html
    }

    // 2. View single hotel details
    @GetMapping("/{id}")
    public String viewHotel(@PathVariable Long id, Model model) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        if (hotel.isPresent()) {
            model.addAttribute("hotel", hotel.get());
            return "hotel_details"; // Renders hotel_details.html
        } else {
            return "redirect:/hotels";
        }
    }

    // 3. Show booking form
    @GetMapping("/{id}/book")
    public String showBookingForm(@PathVariable Long id, Model model) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        if (hotel.isPresent()) {
            model.addAttribute("hotel", hotel.get());
            return "hotel_booking_form"; // Renders hotel_booking_form.html
        } else {
            return "redirect:/hotels";
        }
    }

    // 4. Handle booking form submission
    @PostMapping("/{id}/book")
    public String processBooking(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOutDate,
            @RequestParam int adults,
            @RequestParam int children,
            @RequestParam String roomType,
            Model model
    ) {
        Optional<Hotel> optionalHotel = hotelService.getHotelById(id);
        if (optionalHotel.isEmpty()) {
            return "redirect:/hotels";
        }

        Hotel hotel = optionalHotel.get();

        // Calculate total nights and price
        long totalNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        BigDecimal totalPrice = hotel.getPricePerNight().multiply(BigDecimal.valueOf(totalNights));

        HotelBooking booking = new HotelBooking();
        booking.setHotel(hotel);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setAdults(adults);
        booking.setChildren(children);
        booking.setRoomType(roomType);
        booking.setTotalNights((int) totalNights);
        booking.setTotalPrice(totalPrice);

        hotelBookingService.saveBooking(booking);

        model.addAttribute("booking", booking);
        return "hotel_confirmation";

    }

    // 5. Search hotels by location and optionally dates
    @GetMapping("/search")
    public String searchHotels(
            @RequestParam String location,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkIn,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOut,
            Model model
    ) {
        List<Hotel> hotels = hotelService.searchHotels(location, checkIn, checkOut);
        model.addAttribute("hotels", hotels);
        model.addAttribute("location", location);
        return "hotel_list"; // Renders filtered list using hotel_list.html
    }
}
