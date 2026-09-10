package com.example.eventpass.controller;

import com.example.eventpass.entity.Venue;
import com.example.eventpass.entity.dto.venue.CreateVenueRequest;
import com.example.eventpass.entity.dto.venue.VenueResponse;
import com.example.eventpass.persistence.VenueRepository;
import com.example.eventpass.service.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venue")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService){
        this.venueService = venueService;
    }

    @GetMapping()
    public ResponseEntity<List<VenueResponse>> getAllVenue(){
        List<VenueResponse> venues =  venueService.getallVenue();
        return ResponseEntity.ok(venues);
    }

    @PostMapping()
    public ResponseEntity<VenueResponse> createVenue(@RequestBody CreateVenueRequest venue){
        VenueResponse createdVenue = venueService.createVenue(venue);
        return ResponseEntity.status(201).body(createdVenue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> getVenueById(@PathVariable Long id){
        VenueResponse venue = venueService.getVenueById(id);
        return ResponseEntity.ok(venue);
    }
}
