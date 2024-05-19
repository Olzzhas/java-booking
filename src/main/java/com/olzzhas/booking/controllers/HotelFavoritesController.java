package com.olzzhas.booking.controllers;

import com.olzzhas.booking.hotel.HotelFavorites;
import com.olzzhas.booking.services.HotelFavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/hotel/favorites")
@RequiredArgsConstructor
public class HotelFavoritesController {

    private final HotelFavoritesService service;

    @PostMapping("/add")
    public HotelFavorites addToFavorites(@RequestParam("userId") Integer userId, @RequestParam("hotelId") Integer hotelId) {
        return ResponseEntity.ok(service.addToFavorites(userId, hotelId)).getBody();
    }

    @GetMapping("/get/{userId}")
    public List<HotelFavorites> getAllFavoritesByUserId(@PathVariable Integer userId) {
        return service.getAllFavoritesByUserId(userId);
    }

    @DeleteMapping("delete")
    public void deleteFromFavorites(@RequestParam("userId") Integer userId, @RequestParam("hotelId") Integer hotelId) {
        service.deleteFromFavorites(userId, hotelId);
    }
}
