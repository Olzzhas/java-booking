package com.olzzhas.booking.repository;

import com.olzzhas.booking.hotel.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Integer> {

    List<HotelReservation> findByUserId(Integer userId);

    List<HotelReservation> findByHotelId(Integer hotelId);

    boolean existsByHotelIdAndCheckInDateBetweenOrHotelIdAndCheckOutDateBetween(Integer hotelId, LocalDate checkInDate, LocalDate checkOutDate, Integer hotelId1, LocalDate checkInDate1, LocalDate checkOutDate1);

    boolean existsByHotelIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
            Integer hotelId, LocalDate checkOutDate, LocalDate checkInDate);

    List<HotelReservation> findByHotelIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
            Integer hotelId, LocalDate checkOutDate, LocalDate checkInDate);

}
