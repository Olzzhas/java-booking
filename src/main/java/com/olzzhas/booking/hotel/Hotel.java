package com.olzzhas.booking.hotel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private int capacity;
    private int price;
    private String location;
    private String image_url;
    @Embedded
    private HotelDetails details;
}

