package com.olzzhas.booking.services;

import com.olzzhas.booking.exception.ApiRequestException;
import com.olzzhas.booking.hotel.HotelReservation;
import com.olzzhas.booking.repository.HotelReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional // Important for ensuring data consistency
public class HotelReservationService {

    private final HotelReservationRepository repository;

    public HotelReservationService(HotelReservationRepository repository) {
        this.repository = repository;
    }

    public HotelReservation createReservation(HotelReservation reservation) {
        if (reservation.getCheckInDate().isAfter(reservation.getCheckOutDate())) {
            throw new ApiRequestException("This email is already in use");
        }

        if (repository.existsByHotelIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
                reservation.getHotelId(),
                reservation.getCheckOutDate(),
                reservation.getCheckInDate())) {

            throw new ApiRequestException("Overlapping reservation exists for this hotel and dates.");
        }

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
        // 1. Check if Reservation Exists
        Optional<HotelReservation> existingReservationOptional = repository.findById(reservation.getId());
        if (existingReservationOptional.isEmpty()) {
            throw new EntityNotFoundException("Reservation not found with ID: " + reservation.getId());
        }

        HotelReservation existingReservation = existingReservationOptional.get();

        // 2. Check if Check-out is After Check-in
        if (reservation.getCheckInDate().isAfter(reservation.getCheckOutDate())) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }

        // 3. (Optional) Check for Conflicts with Other Bookings
        if (repository.existsByHotelIdAndCheckInDateBetweenOrHotelIdAndCheckOutDateBetween(
                reservation.getHotelId(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate(),
                reservation.getHotelId(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate()))
        {
            throw new IllegalArgumentException("Reservation conflicts with existing bookings.");
        }

        // 4. Update and Save (using existingReservation to avoid potential issues)
        existingReservation.setCheckInDate(reservation.getCheckInDate());
        existingReservation.setCheckOutDate(reservation.getCheckOutDate());
        // ... update other fields as needed

        return repository.save(existingReservation);
    }


    public void deleteReservation(Integer id) {
        repository.deleteById(id);
    }

    public List<LocalDate> getAvailableDates(Integer hotelId, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new ApiRequestException("Start date cannot be after end date.");
        }

        List<HotelReservation> existingReservations = repository.findByHotelIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
                hotelId, endDate, startDate);

        List<LocalDate> allDates = startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());

        for (HotelReservation reservation : existingReservations) {
            LocalDate date = reservation.getCheckInDate();
            while (!date.isAfter(reservation.getCheckOutDate())) {
                allDates.remove(date);
                date = date.plusDays(1);
            }
        }

        return allDates;
    }

}
