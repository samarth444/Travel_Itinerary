package com.travel.travel_itinerary.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

    @Column(name = "price_per_night")
    private Integer pricePerNight;

    @Column(name = "total_nights")
    private Integer totalNights;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "room_type")
    private String roomType;

    private Integer guests;

    // Default constructor
    public Hotel() {}

    // Constructor with all fields (except id)
    public Hotel(String name, String location, LocalDate checkInDate, LocalDate checkOutDate,
                 Integer pricePerNight, Integer totalNights, Integer totalPrice,
                 String roomType, Integer guests) {
        this.name = name;
        this.location = location;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.pricePerNight = pricePerNight;
        this.totalNights = totalNights;
        this.totalPrice = totalPrice;
        this.roomType = roomType;
        this.guests = guests;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }

    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }

    public Integer getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(Integer pricePerNight) { this.pricePerNight = pricePerNight; }

    public Integer getTotalNights() { return totalNights; }
    public void setTotalNights(Integer totalNights) { this.totalNights = totalNights; }

    public Integer getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Integer totalPrice) { this.totalPrice = totalPrice; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public Integer getGuests() { return guests; }
    public void setGuests(Integer guests) { this.guests = guests; }
}
