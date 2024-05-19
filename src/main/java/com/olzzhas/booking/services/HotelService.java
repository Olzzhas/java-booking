package com.olzzhas.booking.services;

import com.olzzhas.booking.hotel.Hotel;
import com.olzzhas.booking.hotel.HotelDetails;
import com.olzzhas.booking.hotel.HotelRequest;
import com.olzzhas.booking.hotel.HotelResponse;
import com.olzzhas.booking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository repository;
    public HotelResponse create(HotelRequest request) {
        Hotel hotel = new Hotel();
        hotel.setTitle(request.getTitle());
        hotel.setDescription(request.getDescription());
        hotel.setCapacity(request.getCapacity());
        hotel.setLocation(request.getLocation());
        hotel.setPrice(request.getPrice());
        hotel.setImage_url(request.getImage_url());
        hotel.setDetails(request.getDetails());
        repository.save(hotel);
        return HotelResponse.builder()
                .id(hotel.getId())
                .title(hotel.getTitle())
                .description(hotel.getDescription())
                .details(hotel.getDetails())
                .capacity(hotel.getCapacity())
                .image_url(hotel.getImage_url())
                .location(hotel.getLocation())
                .price(hotel.getPrice())
                .build();
    }

    public void delete(int hotelId) {
        repository.deleteById(hotelId);
    }

    public HotelResponse update(int hotelId, HotelRequest request) throws ChangeSetPersister.NotFoundException {
        Optional<Hotel> optionalHotel = repository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotel.setTitle(request.getTitle());
            hotel.setDescription(request.getDescription());
            hotel.setCapacity(request.getCapacity());
            hotel.setLocation(request.getLocation());
            hotel.setPrice(request.getPrice());
            hotel.setImage_url(request.getImage_url());

            repository.save(hotel);
            return HotelResponse.builder()
                    .id(hotelId)
                    .title(request.getTitle())
                    .capacity(request.getCapacity())
                    .location(request.getLocation())
                    .price(request.getPrice())
                    .image_url(request.getImage_url())
                    .description(request.getDescription())
                    .build();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public HotelResponse read(int hotelId) throws NotFoundException {
        Optional<Hotel> optionalHotel = repository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            return HotelResponse.builder()
                    .id(hotelId)
                    .title(hotel.getTitle())
                    .capacity(hotel.getCapacity())
                    .location(hotel.getLocation())
                    .price(hotel.getPrice())
                    .image_url(hotel.getImage_url())
                    .description(hotel.getDescription())
                    .details(hotel.getDetails())
                    .build();
        } else {
            throw new NotFoundException();
        }
    }

    public List<HotelResponse> readAll() throws NotFoundException {
        List<Hotel> hotels = repository.findAll(); // Get all hotels

        if (hotels.isEmpty()) {
            throw new NotFoundException(); // Throw an exception if no hotels are found
        }

        return hotels.stream()               // Use streams for concise transformation
                .map(hotel -> HotelResponse.builder()
                        .id(hotel.getId())
                        .title(hotel.getTitle())
                        .capacity(hotel.getCapacity())
                        .location(hotel.getLocation())
                        .price(hotel.getPrice())
                        .image_url(hotel.getImage_url())
                        .description(hotel.getDescription())
                        .details(hotel.getDetails())
                        .build())
                .collect(Collectors.toList());  // Collect the responses into a List
    }


}
