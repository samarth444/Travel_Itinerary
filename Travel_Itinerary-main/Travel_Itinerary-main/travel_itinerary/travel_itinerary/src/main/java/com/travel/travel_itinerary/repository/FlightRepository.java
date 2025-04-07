package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE LOWER(TRIM(f.source)) = LOWER(TRIM(:source)) AND LOWER(TRIM(f.destination)) = LOWER(TRIM(:destination))")
    List<Flight> findFlightsIgnoreCase(@Param("source") String source, @Param("destination") String destination);

    Optional<Flight> findByFlightNumber(String flightNumber);
}
