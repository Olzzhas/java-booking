package com.olzzhas.booking.repository;

import com.olzzhas.booking.hotel.Hotel;
import com.olzzhas.booking.hotel.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Integer> {

    List<HotelReservation> findByUserId(Integer userId);

    List<HotelReservation> findByHotelId(Integer hotelId);
}
