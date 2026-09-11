package com.example.eventpass.controller;

import com.example.eventpass.entity.dto.event.CreateEventRequest;
import com.example.eventpass.entity.dto.event.CreateEventResponse;
import com.example.eventpass.entity.dto.event.EventResponse;
import com.example.eventpass.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

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
}
