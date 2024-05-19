package com.olzzhas.booking.services;

import com.olzzhas.booking.auth.AuthenticationRequest;
import com.olzzhas.booking.auth.AuthenticationResponse;
import com.olzzhas.booking.auth.RegisterRequest;
import com.olzzhas.booking.exception.ApiRequestException;
import com.olzzhas.booking.user.Role;
import com.olzzhas.booking.user.User;
import com.olzzhas.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .CreatedAt(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        repository.save(user);



        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .build();


    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user  = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
    }
}
