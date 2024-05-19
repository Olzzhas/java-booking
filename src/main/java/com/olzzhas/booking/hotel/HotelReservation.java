package com.olzzhas.booking.hotel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class HotelReservation {
    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    private Integer userId;

    @NonNull
    private Integer hotelId;

    @NonNull
    private LocalDate checkInDate;

    @NonNull
    private LocalDate checkOutDate;

}