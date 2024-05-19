package com.olzzhas.booking.hotel;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDetails {
    private int area;
    private int roomCount;
    private int floorCount;
}
