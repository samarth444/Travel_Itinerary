package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Transactional(readOnly = true)
    List<Hotel> findByLocationIgnoreCase(String location);

    @Transactional(readOnly = true)
    Page<Hotel> findByLocationIgnoreCaseAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
            String location,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            Pageable pageable
    );
}
