package com.olzzhas.booking.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {
    private Integer id;
    private String title;
    private String description;
    private int capacity;
    private int price;
    private String location;
    private String image_url;
    private HotelDetails details;
}
