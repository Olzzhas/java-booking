package com.olzzhas.booking.controllers;

import com.olzzhas.booking.hotel.HotelRequest;
import com.olzzhas.booking.hotel.HotelResponse;
import com.olzzhas.booking.services.HotelService;
import com.olzzhas.booking.services.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;

    @PostMapping("/create")
    public ResponseEntity<HotelResponse> createHotel(@RequestBody HotelRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HotelResponse> getHotel(@PathVariable("id") int id) throws NotFoundException {
        return ResponseEntity.ok(service.read(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HotelResponse> updateHotel(@PathVariable("id") int id, @RequestBody HotelRequest request) throws NotFoundException, ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
