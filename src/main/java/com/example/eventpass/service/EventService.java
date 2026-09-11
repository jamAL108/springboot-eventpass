package com.example.eventpass.service;

import com.example.eventpass.entity.Event;
import com.example.eventpass.entity.EventSeat;
import com.example.eventpass.entity.Seat;
import com.example.eventpass.entity.Venue;
import com.example.eventpass.entity.dto.EventSeatResponse;
import com.example.eventpass.entity.dto.event.CreateEventRequest;
import com.example.eventpass.entity.dto.event.CreateEventResponse;
import com.example.eventpass.entity.dto.event.EventResponse;
import com.example.eventpass.entity.enums.EventSeatStatus;
import com.example.eventpass.exceptions.EventNotFoundException;
import com.example.eventpass.exceptions.VenueNotFoundException;
import com.example.eventpass.persistence.EventRepository;
import com.example.eventpass.persistence.EventSeatRepository;
import com.example.eventpass.persistence.SeatRepository;
import com.example.eventpass.persistence.VenueRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final SeatRepository seatRepository;
    private final EventSeatRepository eventSeatRepository;
    private final ModelMapper modelMapper;

    public EventService(EventRepository eventRepository,EventSeatRepository eventSeatRepository, VenueRepository venueRepository, SeatRepository seatRepository, ModelMapper modelMapper){
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.seatRepository = seatRepository;
        this.eventSeatRepository = eventSeatRepository;
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

    @Transactional
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
        List<Seat> seats = seatRepository.getAllSeatByVenueId(venue.getId());
        List<EventSeat> eventSeats = new ArrayList<>();
        for (Seat seat : seats) {
            EventSeat eventSeat = new EventSeat();
            eventSeat.setEvent(savedEvent);
            eventSeat.setSeat(seat);
            eventSeat.setStatus(EventSeatStatus.AVAILABLE);
            eventSeats.add(eventSeat);
        }
        eventSeatRepository.saveAll(eventSeats);
        return modelMapper.map(savedEvent, EventResponse.class);
    }

    public List<EventSeatResponse> getSeatsByEventId(Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new EventNotFoundException("Event not Found with id: " + id));
        return eventSeatRepository.findSeatsByEventId(event.getId());
    }
}
