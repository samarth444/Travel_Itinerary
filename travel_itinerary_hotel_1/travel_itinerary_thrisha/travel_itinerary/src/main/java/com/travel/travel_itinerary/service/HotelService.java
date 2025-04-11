package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Hotel;
import com.travel.travel_itinerary.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    // ✅ Find hotels by location (case-insensitive)
    public List<Hotel> findHotelsByLocation(String location) {
        return hotelRepository.findByLocationIgnoreCase(location.trim());
    }

    // ✅ Find hotel by ID
    public Optional<Hotel> findHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    // ✅ Paginated hotel search by availability dates
    public Page<Hotel> findHotelsPaginated(String location, LocalDate checkIn, LocalDate checkOut, Pageable pageable) {
        return hotelRepository.findByLocationIgnoreCaseAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
                location.trim(), checkIn, checkOut, pageable
        );
    }

    // ✅ Save new or updated hotel
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
}
