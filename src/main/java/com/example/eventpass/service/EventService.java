package com.example.eventpass.service;

import com.example.eventpass.entity.Event;
import com.example.eventpass.entity.Venue;
import com.example.eventpass.entity.dto.event.CreateEventRequest;
import com.example.eventpass.entity.dto.event.CreateEventResponse;
import com.example.eventpass.entity.dto.event.EventResponse;
import com.example.eventpass.exceptions.EventNotFoundException;
import com.example.eventpass.exceptions.VenueNotFoundException;
import com.example.eventpass.persistence.EventRepository;
import com.example.eventpass.persistence.VenueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final ModelMapper modelMapper;

    public EventService(EventRepository eventRepository,VenueRepository venueRepository, ModelMapper modelMapper){
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.modelMapper = modelMapper;
    }

    public EventResponse findById(Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new EventNotFoundException("Event not Found with id: " + id));
        return modelMapper.map(event, EventResponse.class);
    }

    public List<EventResponse> getAllEvents(){
        return eventRepository.findAll()
                .stream()
                .map(event -> modelMapper.map(event,EventResponse.class))
                .toList();
    }

    public EventResponse addEvent(CreateEventRequest request) {
        Event event = new Event();
        event.setName(request.getName());
        event.setEventDate(request.getEventDate());
        Venue venue = venueRepository.findById(request.getVenueId())
                .orElseThrow(() -> new VenueNotFoundException(
                        "Venue not found with id " + request.getVenueId()
                ));
        event.setVenue(venue);
        Event savedEvent = eventRepository.save(event);
        return modelMapper.map(savedEvent, EventResponse.class);
    }
}
