package com.travel.travel_itinerary.model;

import jakarta.persistence.*;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airline;
    private String flightNumber;
    private String fromLocation;
    private double price;
    private String toLocation;

    @Column(name = "seats_available", nullable = false)
    private int seatsAvailable;  // This field represents the available seats

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

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    // Optional: Override toString() for better debug output
    @Override
    public String toString() {
        return "Flight [id=" + id + ", airline=" + airline + ", flightNumber=" + flightNumber +
                ", fromLocation=" + fromLocation + ", price=" + price + ", toLocation=" + toLocation +
                ", seatsAvailable=" + seatsAvailable + "]";
    }
}
