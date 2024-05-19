package com.olzzhas.booking.services;

import com.olzzhas.booking.hotel.HotelReservation;
import com.olzzhas.booking.repository.HotelReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional // Important for ensuring data consistency
public class HotelReservationService {

    private final HotelReservationRepository repository;

    public HotelReservationService(HotelReservationRepository repository) {
        this.repository = repository;
    }

    public HotelReservation createReservation(HotelReservation reservation) {
        // Basic validation (more comprehensive validation would be needed in a real app)
        if (reservation.getCheckInDate().isAfter(reservation.getCheckOutDate())) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }

        // You might want to check availability here before confirming the reservation
        // ...

        return repository.save(reservation);
    }

    public Optional<HotelReservation> getReservationById(Integer id) {
        return repository.findById(id);
    }

    public List<HotelReservation> getReservationsByUserId(Integer userId) {
        return repository.findByUserId(userId);
    }


    public List<HotelReservation> getReservationsByHotelId(Integer hotelId) {
        return repository.findByHotelId(hotelId);
    }

    public HotelReservation updateReservation(HotelReservation reservation) {
        // Validation (similar to createReservation, plus checking if reservation exists)
        // ...

        return repository.save(reservation);
    }

    public void deleteReservation(Integer id) {
        repository.deleteById(id);
    }
}
