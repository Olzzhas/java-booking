package com.olzzhas.booking.services;

import com.olzzhas.booking.hotel.Hotel;
import com.olzzhas.booking.hotel.HotelRequest;
import com.olzzhas.booking.hotel.HotelResponse;
import com.olzzhas.booking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository repository;
    public HotelResponse create(HotelRequest request) {
        Hotel hotel = new Hotel();
        hotel.setTitle(request.getTitle());
        repository.save(hotel);
        return HotelResponse.builder().title(request.getTitle()).build();
    }

    public void delete(int hotelId) {
        repository.deleteById(hotelId);
    }

    public HotelResponse update(int hotelId, HotelRequest request) throws ChangeSetPersister.NotFoundException {
        Optional<Hotel> optionalHotel = repository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotel.setTitle(request.getTitle());

            repository.save(hotel);
            return HotelResponse.builder().title(request.getTitle()).build();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public HotelResponse read(int hotelId) throws NotFoundException {
        Optional<Hotel> optionalHotel = repository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            return HotelResponse.builder().title(hotel.getTitle()).build();
        } else {
            throw new NotFoundException();
        }
    }

}
