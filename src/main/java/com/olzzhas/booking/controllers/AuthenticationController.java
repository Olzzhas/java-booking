package com.olzzhas.booking.controllers;

import com.olzzhas.booking.auth.AuthenticationRequest;
import com.olzzhas.booking.auth.AuthenticationResponse;
import com.olzzhas.booking.auth.RegisterRequest;
import com.olzzhas.booking.exception.ApiRequestException;
import com.olzzhas.booking.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
            throw new ApiRequestException("api throws exception while creating a new user");
//            return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    public static class HotelController {

    }
}
