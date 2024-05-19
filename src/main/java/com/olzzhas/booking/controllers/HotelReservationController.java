package com.olzzhas.booking.controllers;

import com.olzzhas.booking.hotel.HotelReservation;
import com.olzzhas.booking.services.HotelReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotel/reservation")
@RequiredArgsConstructor
public class HotelReservationController {

    private final HotelReservationService service;

    @PostMapping("/create")
    public ResponseEntity<HotelReservation> createHotel(@RequestBody HotelReservation reservation){
        return ResponseEntity.ok(service.createReservation(reservation));
    }
}
