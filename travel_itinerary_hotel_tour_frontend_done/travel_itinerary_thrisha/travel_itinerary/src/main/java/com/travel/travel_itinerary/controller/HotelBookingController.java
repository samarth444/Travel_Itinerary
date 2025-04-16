package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Hotel;
import com.travel.travel_itinerary.model.HotelBooking;
import com.travel.travel_itinerary.service.HotelBookingService;
import com.travel.travel_itinerary.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Controller
@RequestMapping("/hotelbookings")
public class HotelBookingController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelBookingService hotelBookingService;

    @GetMapping("/{hotelId}/book")
    public String showBookingForm(@PathVariable Long hotelId, Model model) {
        Optional<Hotel> optionalHotel = hotelService.getHotelById(hotelId);
        if (optionalHotel.isPresent()) {
            model.addAttribute("hotel", optionalHotel.get());
            return "hotel_booking_form";
        } else {
            return "redirect:/hotels";
        }
    }

    @PostMapping("/{hotelId}/book")
    public String processBooking(
            @PathVariable Long hotelId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOutDate,
            @RequestParam int adults,
            @RequestParam int children,
            @RequestParam String roomType,
            @RequestParam String guestName,
            @RequestParam String email,
            @RequestParam String phone,
            Model model
    ) {
        Optional<Hotel> optionalHotel = hotelService.getHotelById(hotelId);
        if (optionalHotel.isEmpty()) {
            return "redirect:/hotels";
        }

        Hotel hotel = optionalHotel.get();
        long totalNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        BigDecimal totalPrice = hotel.getPricePerNight().multiply(BigDecimal.valueOf(totalNights));

        HotelBooking booking = HotelBooking.builder()
                .hotel(hotel)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .adults(adults)
                .children(children)
                .roomType(roomType)
                .guestName(guestName)
                .email(email)
                .phone(phone)
                .totalNights((int) totalNights)
                .totalPrice(totalPrice)
                .build();

        hotelBookingService.saveBooking(booking);
        model.addAttribute("booking", booking);
        return "hotel_booking_confirmation";
    }
}
