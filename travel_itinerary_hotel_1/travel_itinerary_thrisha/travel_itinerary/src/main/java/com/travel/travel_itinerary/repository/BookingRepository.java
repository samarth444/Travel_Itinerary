package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.Booking;
import com.travel.travel_itinerary.model.User;
import com.travel.travel_itinerary.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Booking entity.
 * Provides methods to perform CRUD operations and custom queries.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Find all bookings for a specific user.
     *
     * @param user the user whose bookings are to be found
     * @return list of bookings
     */
    List<Booking> findByUser(User user);

    /**
     * Find bookings for a user associated with a specific flight.
     *
     * @param user the user who made the booking
     * @param flight the flight booked
     * @return list of bookings
     */
    List<Booking> findByUserAndFlight(User user, Flight flight);

    /**
     * Custom JPQL query to fetch all bookings along with their associated user and flight details.
     *
     * @return list of bookings with joined user and flight data
     */
    @Query("SELECT b FROM Booking b JOIN FETCH b.user JOIN FETCH b.flight")
    List<Booking> findAllWithDetails();
}
