package com.olzzhas.booking.controllers;

import com.olzzhas.booking.hotel.HotelReservation;
import com.olzzhas.booking.services.HotelReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hotel/reservation")
@RequiredArgsConstructor
public class HotelReservationController {

    private final HotelReservationService service;

    @PostMapping("/create")
    public ResponseEntity<HotelReservation> createHotel(@RequestBody HotelReservation reservation){
        return ResponseEntity.ok(service.createReservation(reservation));
    }

    @GetMapping("/available/{hotelId}/{dateIn}/{dateOut}")
    public ResponseEntity<List<LocalDate>> getAllReservations(@PathVariable("hotelId") Integer hotelId, @PathVariable("dateIn") LocalDate dateIn, @PathVariable("dateOut") LocalDate dateOut){
        return ResponseEntity.ok(service.getAvailableDates(hotelId, dateIn, dateOut));
    }

    @GetMapping("/get/{id}")
    public Optional<HotelReservation> getReservationById(@PathVariable("id") Integer id){
        return service.getReservationById(id);
    }

    @GetMapping("/list/user/{userId}")
    public List<HotelReservation> getReservationsByUserId(@PathVariable("userId") Integer userId) {
        return service.getReservationsByUserId(userId);
    }

    @GetMapping("/list/hotel/{hotelId}")
    public List<HotelReservation> getReservationsByHotelId(@PathVariable("hotelId") Integer hotelId) {
        return service.getReservationsByHotelId(hotelId);
    }

    @PutMapping("/update")
    public HotelReservation updateReservation(HotelReservation reservation){
        return service.updateReservation(reservation);
    }

    @DeleteMapping("/delete")
    public void deleteReservation(Integer id) {
        service.deleteReservation(id);
    }
}
