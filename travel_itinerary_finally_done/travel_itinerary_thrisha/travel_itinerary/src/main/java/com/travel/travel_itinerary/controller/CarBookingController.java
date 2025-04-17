package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Car;
import com.travel.travel_itinerary.model.CarBooking;
import com.travel.travel_itinerary.service.CarBookingService;
import com.travel.travel_itinerary.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
public class CarBookingController {

    @Autowired
    private CarBookingService carBookingService;

    @Autowired
    private CarService carService;

    @GetMapping("/cars/book/{id}")
    public String showBookingForm(@PathVariable("id") Long carId, Model model) {
        Car car = carService.getCarById(carId).orElse(null);
        if (car == null) {
            model.addAttribute("error", "Car not found or no longer available.");
            return "car-list";
        }

        model.addAttribute("car", car);
        return "car-booking-form";
    }

    @PostMapping("/cars/book")
    public String bookCar(@RequestParam("carId") Long carId,
                          @RequestParam("userName") String userName,
                          @RequestParam("email") String email,
                          @RequestParam("phoneNumber") String phoneNumber,
                          @RequestParam("pickupDate") String pickupDate,
                          @RequestParam("dropoffDate") String dropoffDate,
                          @RequestParam("location") String location,
                          Model model) {

        // Convert String to LocalDate
        LocalDate pickupLocalDate = LocalDate.parse(pickupDate);  // Convert pickupDate to LocalDate
        LocalDate dropoffLocalDate = LocalDate.parse(dropoffDate);  // Convert dropoffDate to LocalDate

        try {
            // Pass the individual parameters to the service method
            CarBooking carBooking = carBookingService.bookCar(
                    carId, userName, email, phoneNumber, pickupDate, dropoffDate, location);
            model.addAttribute("booking", carBooking);
            return "car-booking-confirmation";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "car-booking-form";
        }
    }
}