package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Car;
import com.travel.travel_itinerary.model.CarBooking;
import com.travel.travel_itinerary.repository.CarBookingRepository;
import com.travel.travel_itinerary.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CarBookingService {

    @Autowired
    private CarBookingRepository carBookingRepository;

    @Autowired
    private CarRepository carRepository;

    public CarBooking bookCar(Long carId, String userName, String email, String phoneNumber,
                              String pickupDate, String dropoffDate, String location) {

        Car car = carRepository.findById(carId).orElseThrow(() ->
                new IllegalArgumentException("Car not found"));

        if (car.getAvailableQuantity() <= 0) {
            throw new IllegalArgumentException("Car is out of stock");
        }

        // Convert pickupDate and dropoffDate to LocalDate
        LocalDate pickupLocalDate = LocalDate.parse(pickupDate);
        LocalDate dropoffLocalDate = LocalDate.parse(dropoffDate);

        // Calculate the number of days for the booking
        long daysBetween = ChronoUnit.DAYS.between(pickupLocalDate, dropoffLocalDate);

        // Calculate total cost (assuming price per day)
        double totalCost = daysBetween * car.getPricePerDay();

        CarBooking booking = new CarBooking();
        booking.setCarId(carId);
        booking.setUserName(userName);
        booking.setEmail(email);
        booking.setPhoneNumber(phoneNumber);
        booking.setPickupDate(pickupDate);
        booking.setDropoffDate(dropoffDate);
        booking.setLocation(location);
        booking.setBookingDate(LocalDate.now());  // Set booking date to today
        booking.setCarPricePerDay(car.getPricePerDay());  // Set price per day
        booking.setTotalCost(totalCost);  // Set total cost

        carBookingRepository.save(booking);  // Save booking

        // Decrease available quantity of the car
        car.setAvailableQuantity(car.getAvailableQuantity() - 1);
        carRepository.save(car);  // Update car quantity in database

        return booking;
    }
}