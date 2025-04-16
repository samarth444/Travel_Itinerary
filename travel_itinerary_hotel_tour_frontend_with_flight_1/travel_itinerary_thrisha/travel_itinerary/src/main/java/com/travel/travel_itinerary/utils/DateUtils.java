package com.travel.travel_itinerary.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    /**
     * Convert java.util.Date to LocalDate
     */
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Convert LocalDate to java.util.Date
     */
    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
