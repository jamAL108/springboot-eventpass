package com.example.eventpass.service.Impl;

import com.example.eventpass.entity.Seat;
import com.example.eventpass.entity.Venue;
import com.example.eventpass.entity.dto.seat.CreateSeatRequest;
import com.example.eventpass.entity.dto.seat.VenueSeatResponse;
import com.example.eventpass.exceptions.VenueNotFoundException;
import com.example.eventpass.persistence.SeatRepository;
import com.example.eventpass.persistence.VenueRepository;
import com.example.eventpass.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final VenueRepository venueRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<VenueSeatResponse> getAllSeats(Long venueId) {
        Boolean venueExists = venueRepository.existsById(venueId);
        if(!venueExists){
            throw new VenueNotFoundException("Venue not Found with id: " + venueId);
        }
        return seatRepository.getAllSeatByVenueId(venueId)
                .stream()
                .map(seat -> modelMapper.map(seat,VenueSeatResponse.class))
                .toList();
    }

    @Override
    public VenueSeatResponse addSeat(Long venueId, CreateSeatRequest createSeatRequest){
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new VenueNotFoundException("Venue not Found with id: " + venueId));

        Seat seat = modelMapper.map(createSeatRequest,Seat.class);
        seat.setVenue(venue);

        Seat savedSeat = seatRepository.save(seat);
        return modelMapper.map(savedSeat,VenueSeatResponse.class);
    }
}
