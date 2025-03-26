package com.travel.travel_itinerary.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate date;
    private String destination;

    // Default constructor
    public Booking() {}

    // Constructor
    public Booking(User user, LocalDate date, String destination) {
        this.user = user;
        this.date = date;
        this.destination = destination;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {  // <--- Ensure this method exists
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDestination() { // <--- Ensure this method exists
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
