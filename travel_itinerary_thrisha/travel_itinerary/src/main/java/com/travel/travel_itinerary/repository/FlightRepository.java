package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    // Custom query that trims and ignores case for source and destination
    @Transactional(readOnly = true)
    @Query("SELECT f FROM Flight f WHERE LOWER(TRIM(f.source)) = LOWER(TRIM(:source)) AND LOWER(TRIM(f.destination)) = LOWER(TRIM(:destination))")
    List<Flight> findFlightsIgnoreCase(@Param("source") String source, @Param("destination") String destination);

    // Find by flight number
    @Transactional(readOnly = true)
    Optional<Flight> findByFlightNumber(String flightNumber);

    // Optional derived method with pagination and case-insensitivity
    @Transactional(readOnly = true)
    Page<Flight> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination, Pageable pageable);
}
