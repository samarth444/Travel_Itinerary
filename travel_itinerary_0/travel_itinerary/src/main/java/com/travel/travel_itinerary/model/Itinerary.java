package com.travel.travel_itinerary.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destination;
    private String startDate;
    private String endDate;

    @ElementCollection
    private List<String> activities;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Add this field

    // Getters and Setters
    public Long getId() { return id; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public List<String> getActivities() { return activities; }
    public void setActivities(List<String> activities) { this.activities = activities; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
