package com.travel.travel_itinerary.service;

import com.travel.travel_itinerary.model.Hotel;
import com.travel.travel_itinerary.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// Service Interface
public interface HotelService {
    List<Hotel> getAllHotels();

    Optional<Hotel> getHotelById(Long id);

    List<Hotel> searchHotels(String location, LocalDate checkIn, LocalDate checkOut);

    Hotel saveHotel(Hotel hotel);

    void deleteHotel(Long id);

    List<Hotel> getHotelsByLocation(String location);
}

// Service Implementation
@Service
class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public List<Hotel> searchHotels(String location, LocalDate checkIn, LocalDate checkOut) {
        // Since Hotel entity has no dates, we only filter by location for now
        return hotelRepository.findByLocationContainingIgnoreCase(location);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<Hotel> getHotelsByLocation(String location) {
        return hotelRepository.findByLocationIgnoreCase(location);
    }

}
