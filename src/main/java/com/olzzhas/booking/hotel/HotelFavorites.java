package com.olzzhas.booking.hotel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorites")
public class HotelFavorites {
    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    private Integer userId;

    @NonNull
    private Integer hotelId;
}