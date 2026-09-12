package com.example.eventpass.service;

import com.example.eventpass.entity.dto.venue.CreateVenueRequest;
import com.example.eventpass.entity.dto.venue.VenueResponse;

import java.util.List;

public interface VenueService {

    List<VenueResponse> getallVenue();
    VenueResponse createVenue(CreateVenueRequest venueRequest);
    VenueResponse getVenueById(Long id);
    void removeVanue(Long id);
}
