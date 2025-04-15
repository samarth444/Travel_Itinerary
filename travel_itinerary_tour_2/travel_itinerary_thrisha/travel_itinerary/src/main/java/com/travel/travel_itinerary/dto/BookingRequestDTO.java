package com.travel.travel_itinerary.dto;

import java.time.LocalDate;

/**
 * DTO for flight booking requests.
 * Contains minimal necessary info to create a Booking.
 */
public class BookingRequestDTO {

    private Long userId;
    private Long flightId;
    private int numberOfSeats;
    private LocalDate bookingDate;

    // Constructors
    public BookingRequestDTO() {
    }

    public BookingRequestDTO(Long userId, Long flightId, int numberOfSeats, LocalDate bookingDate) {
        this.userId = userId;
        this.flightId = flightId;
        this.numberOfSeats = numberOfSeats;
        this.bookingDate = bookingDate;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "BookingRequestDTO{" +
                "userId=" + userId +
                ", flightId=" + flightId +
                ", numberOfSeats=" + numberOfSeats +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
