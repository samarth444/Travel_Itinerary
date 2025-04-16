package com.travel.travel_itinerary.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "hotel_bookings")
public class HotelBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private int adults;
    private int children;

    private String roomType;
    private int totalNights;
    private BigDecimal totalPrice;

    private String guestName;
    private String email;
    private String phone;

    // Constructors

    public HotelBooking() {}

    public HotelBooking(Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate,
                        int adults, int children, String roomType,
                        int totalNights, BigDecimal totalPrice,
                        String guestName, String email, String phone) {
        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.adults = adults;
        this.children = children;
        this.roomType = roomType;
        this.totalNights = totalNights;
        this.totalPrice = totalPrice;
        this.guestName = guestName;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getTotalNights() {
        return totalNights;
    }

    public void setTotalNights(int totalNights) {
        this.totalNights = totalNights;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
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

    // Builder Pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final HotelBooking booking;

        public Builder() {
            booking = new HotelBooking();
        }

        public Builder hotel(Hotel hotel) {
            booking.setHotel(hotel);
            return this;
        }

        public Builder checkInDate(LocalDate date) {
            booking.setCheckInDate(date);
            return this;
        }

        public Builder checkOutDate(LocalDate date) {
            booking.setCheckOutDate(date);
            return this;
        }

        public Builder adults(int adults) {
            booking.setAdults(adults);
            return this;
        }

        public Builder children(int children) {
            booking.setChildren(children);
            return this;
        }

        public Builder roomType(String roomType) {
            booking.setRoomType(roomType);
            return this;
        }

        public Builder totalNights(int nights) {
            booking.setTotalNights(nights);
            return this;
        }

        public Builder totalPrice(BigDecimal price) {
            booking.setTotalPrice(price);
            return this;
        }

        public Builder guestName(String name) {
            booking.setGuestName(name);
            return this;
        }

        public Builder email(String email) {
            booking.setEmail(email);
            return this;
        }

        public Builder phone(String phone) {
            booking.setPhone(phone);
            return this;
        }

        public HotelBooking build() {
            return booking;
        }
    }
}
