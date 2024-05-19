package com.olzzhas.booking.controllers;

import com.olzzhas.booking.auth.AuthenticationRequest;
import com.olzzhas.booking.auth.AuthenticationResponse;
import com.olzzhas.booking.auth.RegisterRequest;
import com.olzzhas.booking.exception.ApiRequestException;
import com.olzzhas.booking.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        try{
            return ResponseEntity.ok(service.register(request));
        } catch (DataAccessException e){
            if (e.getMessage().contains("users_email_key")){
                throw new ApiRequestException("This email is already in use");
            }else{
                throw e;
            }
        }
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
