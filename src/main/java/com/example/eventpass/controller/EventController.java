package com.example.eventpass.controller;

import com.example.eventpass.entity.dto.EventSeatResponse;
import com.example.eventpass.entity.dto.artist.ArtistResponse;
import com.example.eventpass.entity.dto.event.CreateEventRequest;
import com.example.eventpass.entity.dto.event.CreateEventResponse;
import com.example.eventpass.entity.dto.event.EventResponse;
import com.example.eventpass.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id){
        EventResponse event = eventService.findById(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping()
    public ResponseEntity<List<EventResponse>> getAllEvents(){
        List<EventResponse> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping()
    public ResponseEntity<EventResponse> addEvent(@Valid @RequestBody CreateEventRequest request){
        EventResponse createdEvent = eventService.addEvent(request);
        return ResponseEntity.status(201).body(createdEvent);
    }

    @GetMapping("/{id}/seats")
    public ResponseEntity<List<EventSeatResponse>> getSeatsByEvent(@PathVariable Long id){
        List<EventSeatResponse> eventSeats = eventService.getSeatsByEventId(id);
        return ResponseEntity.ok(eventSeats);
    }

    @GetMapping("/{id}/artists")
    public ResponseEntity<List<ArtistResponse>> getArtistsByEvent(@PathVariable Long id){
        List<ArtistResponse> eventArtists = eventService.getArtistsByEventId(id);
        return ResponseEntity.ok(eventArtists);
    }

    @PutMapping("/{eventId}/artists/{artistId}")
    public ResponseEntity<String> addArtistToEvent(@PathVariable Long eventId, @PathVariable Long artistId){
        eventService.addArtistToEvent(eventId, artistId);
        return ResponseEntity.noContent().build();
    }
}
