package com.olzzhas.booking.services;

import com.olzzhas.booking.hotel.HotelFavorites;
import com.olzzhas.booking.repository.HotelFavoritesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HotelFavoritesService {

    private final HotelFavoritesRepository repository;

    public HotelFavoritesService(HotelFavoritesRepository repository) {
        this.repository = repository;
    }

    public HotelFavorites addToFavorites(Integer userId, Integer hotelId) {
        HotelFavorites favorite = HotelFavorites.builder()
                .userId(userId)
                .hotelId(hotelId)
                .build();
        return repository.save(favorite);
    }

    public void deleteFromFavorites(Integer userId, Integer hotelId) {
        repository.deleteByUserIdAndHotelId(userId, hotelId);
    }

    public List<HotelFavorites> getAllFavoritesByUserId(Integer userId) {
        return repository.findByUserId(userId);
    }
}
