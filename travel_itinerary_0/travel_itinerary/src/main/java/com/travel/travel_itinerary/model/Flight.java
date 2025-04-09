package com.travel.travel_itinerary.model;

import jakarta.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String airline;
    private String flightNumber;
    private String departure;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private double price;
    private String baggageInfo;
    private int layovers;

    // Default Constructor
    public Flight() {}

    // Parameterized Constructor
    public Flight(String airline, String flightNumber, String departure, String destination,
                  String departureTime, String arrivalTime, String duration, double price,
                  String baggageInfo, int layovers) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.price = price;
        this.baggageInfo = baggageInfo;
        this.layovers = layovers;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getBaggageInfo() { return baggageInfo; }
    public void setBaggageInfo(String baggageInfo) { this.baggageInfo = baggageInfo; }

    public int getLayovers() { return layovers; }
    public void setLayovers(int layovers) { this.layovers = layovers; }
}
