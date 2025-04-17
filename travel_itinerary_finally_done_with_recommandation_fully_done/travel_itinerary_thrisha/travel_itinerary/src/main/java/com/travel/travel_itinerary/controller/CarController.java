package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.Car;
import com.travel.travel_itinerary.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * Show all cars when visiting /cars
     */
    @GetMapping
    public String showAvailableCars(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "cars";    // Thymeleaf template: cars.html
    }

    /**
     * Handle search by location (and optional type)
     * at POST /cars/search
     */
    @PostMapping("/search")
    public String searchCars(@RequestParam String location,
                             @RequestParam(required = false) String type,
                             Model model) {
        List<Car> cars = carService.searchCars(location, type);
        model.addAttribute("cars", cars);
        return "cars";
    }

    /**
     * Redirect to booking form: GET /cars/book?carId=...
     */
    @GetMapping("/book")
    public String showBookingForm(@RequestParam Long carId,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        Optional<Car> optionalCar = carService.getCarById(carId);
        if (optionalCar.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Car not found.");
            return "redirect:/cars";
        }
        Car car = optionalCar.get();
        if (car.getAvailableQuantity() <= 0) {
            redirectAttributes.addFlashAttribute("error", "This car is currently out of stock.");
            return "redirect:/cars";
        }
        model.addAttribute("car", car);
        model.addAttribute("carBooking", new com.travel.travel_itinerary.model.CarBooking());
        return "car-booking-form";
    }

    @GetMapping("/location/{location}")
    public String getCarsByLocation(@PathVariable String location, Model model) {
        List<Car> cars = carService.getCarsByLocation(location);
        model.addAttribute("cars", cars);
        model.addAttribute("pageTitle", "Cars available in " + location);
        return "car-list"; // 🔁 this should be your Thymeleaf car list template
    }

}