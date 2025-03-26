package com.travel.travel_itinerary.repository;

import com.travel.travel_itinerary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query to find a user by email
    Optional<User> findByEmail(String email);
}
