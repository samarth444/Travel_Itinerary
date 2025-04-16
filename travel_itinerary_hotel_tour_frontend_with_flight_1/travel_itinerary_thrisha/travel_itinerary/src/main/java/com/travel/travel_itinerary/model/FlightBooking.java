package com.travel.travel_itinerary.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * FlightBooking entity representing a flight booking.
 */
@Entity
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    private int numberOfPassengers;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private LocalDate departureDate;

    @Column(nullable = false)
    private String passengerName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    // Constructors
    public FlightBooking() {}

    public FlightBooking(Flight flight, int numberOfPassengers, LocalDate departureDate,
                         String passengerName, String email, String phone) {
        this.flight = flight;
        this.numberOfPassengers = numberOfPassengers;
        this.departureDate = departureDate;
        this.passengerName = passengerName;
        this.email = email;
        this.phone = phone;
        calculateTotalPrice();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
        calculateTotalPrice();
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        calculateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Calculate the total price based on the flight price and number of passengers.
     */
    public void calculateTotalPrice() {
        if (this.flight != null && this.numberOfPassengers > 0) {
            this.totalPrice = this.flight.getPrice() * this.numberOfPassengers;
        }
    }
}
