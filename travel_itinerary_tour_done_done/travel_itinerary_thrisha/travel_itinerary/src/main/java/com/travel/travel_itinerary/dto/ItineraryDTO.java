package com.travel.travel_itinerary.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ItineraryDTO {
    private Long id;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
