package com.travel.travel_itinerary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TourPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double price;

    private String duration;
    private String location;
    private Integer availableSpots;
    private String availabilityCalendar;
    private String imageUrl; // Store image URL

    @JsonIgnore
    @OneToMany(mappedBy = "tourPackage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Itinerary> itineraries = new ArrayList<>();

    // --- Constructors ---

    public TourPackage() {
        // Default constructor
    }

    public TourPackage(String name, String description, Double price, String duration,
                       String location, Integer availableSpots, String availabilityCalendar, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.location = location;
        this.availableSpots = availableSpots;
        this.availabilityCalendar = availabilityCalendar;
        this.imageUrl = imageUrl;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(Integer availableSpots) {
        this.availableSpots = availableSpots;
    }

    public String getAvailabilityCalendar() {
        return availabilityCalendar;
    }

    public void setAvailabilityCalendar(String availabilityCalendar) {
        this.availabilityCalendar = availabilityCalendar;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    // Convenience method to add itinerary
    public void addItinerary(Itinerary itinerary) {
        itineraries.add(itinerary);
        itinerary.setTourPackage(this);
    }

    // --- toString ---

    @Override
    public String toString() {
        return "TourPackage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration='" + duration + '\'' +
                ", location='" + location + '\'' +
                ", availableSpots=" + availableSpots +
                ", availabilityCalendar='" + availabilityCalendar + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
