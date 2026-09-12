package com.example.eventpass.service;

import com.example.eventpass.entity.Venue;
import com.example.eventpass.entity.dto.venue.CreateVenueRequest;
import com.example.eventpass.entity.dto.venue.VenueResponse;
import com.example.eventpass.exceptions.VenueNotFoundException;
import com.example.eventpass.persistence.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;
    private final ModelMapper modelMapper;

    public List<VenueResponse> getallVenue(){
        return venueRepository.findAll()
                .stream()
                .map(venue -> modelMapper.map(venue, VenueResponse.class))
                .toList();
    }

    public VenueResponse createVenue(CreateVenueRequest venueRequest) {
        Venue venue = modelMapper.map(venueRequest, Venue.class);
        Venue createdVenue = venueRepository.save(venue);
        return modelMapper.map(createdVenue, VenueResponse.class);
    }

    public VenueResponse getVenueById(Long id){
        Venue venue =  venueRepository.findById(id)
                .orElseThrow(() -> new VenueNotFoundException("Venue Not Found"));
        return modelMapper.map(venue, VenueResponse.class);
    }

    public void removeVanue(Long id){
        venueRepository.deleteById(id);
    }
}
