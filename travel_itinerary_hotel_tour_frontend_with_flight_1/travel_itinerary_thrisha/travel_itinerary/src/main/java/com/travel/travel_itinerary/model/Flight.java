package com.travel.travel_itinerary.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airline;
    private String departureTime;
    private String flightNumber;
    private String fromLocation;
    private double price;
    private String toLocation;

    private LocalDate departureDate;  // This field represents the departure date

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    // Optional: Override toString() for better debug output
    @Override
    public String toString() {
        return "Flight [id=" + id + ", airline=" + airline + ", departureTime=" + departureTime +
                ", flightNumber=" + flightNumber + ", fromLocation=" + fromLocation +
                ", price=" + price + ", toLocation=" + toLocation + ", departureDate=" + departureDate + "]";
    }
}
