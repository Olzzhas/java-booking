package com.olzzhas.booking.repository;

import com.olzzhas.booking.hotel.HotelFavorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelFavoritesRepository extends JpaRepository<HotelFavorites, Integer> {
    List<HotelFavorites> findByUserId(Integer userId);

    void deleteByUserIdAndHotelId(Integer userId, Integer hotelId);
}
