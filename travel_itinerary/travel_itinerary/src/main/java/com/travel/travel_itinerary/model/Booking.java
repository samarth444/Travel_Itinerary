package com.travel.travel_itinerary.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;  // Ensure this field exists

    @ManyToOne
    private Itinerary itinerary;

    private LocalDate bookingDate;
    private String status;

    // Getters and Setters
    public Long getId() { return id; }

    public User getUser() { return user; }  // Ensure this method exists
    public void setUser(User user) { this.user = user; }

    public Itinerary getItinerary() { return itinerary; }
    public void setItinerary(Itinerary itinerary) { this.itinerary = itinerary; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
