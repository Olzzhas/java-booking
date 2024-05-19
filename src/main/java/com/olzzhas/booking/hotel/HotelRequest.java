package com.olzzhas.booking.hotel;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequest {
    private String title;
    private String description;
    private int capacity;
    private int price;
    private String location;
    private String image_url;
    private HotelDetails details;
}
