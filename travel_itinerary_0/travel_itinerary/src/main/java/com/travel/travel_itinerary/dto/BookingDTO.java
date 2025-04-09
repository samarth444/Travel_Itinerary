package com.travel.travel_itinerary.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingDTO {
    private Long userId;
    private Long itineraryId;
    private LocalDate bookingDate;
    private int numberOfPeople;
}
