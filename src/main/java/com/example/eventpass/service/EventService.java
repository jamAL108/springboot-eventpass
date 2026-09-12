package com.example.eventpass.service;

import com.example.eventpass.entity.*;
import com.example.eventpass.entity.dto.EventSeatResponse;
import com.example.eventpass.entity.dto.artist.ArtistResponse;
import com.example.eventpass.entity.dto.event.CreateEventRequest;
import com.example.eventpass.entity.dto.event.EventResponse;
import com.example.eventpass.entity.enums.EventSeatStatus;
import com.example.eventpass.exceptions.ArtistNotFoundException;
import com.example.eventpass.exceptions.EventNotFoundException;
import com.example.eventpass.exceptions.VenueNotFoundException;
import com.example.eventpass.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final SeatRepository seatRepository;
    private final EventSeatRepository eventSeatRepository;
    private final EventArtistRepository eventArtistRepository;
    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;

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
        return eventSeatRepository.findSeatsByEventId(id);
    }

    public List<ArtistResponse> getArtistsByEventId(Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new EventNotFoundException("Event not Found with id: " + id));
        return eventArtistRepository.findArtistsByEventId(id);
    }

    @Transactional
    public void addArtistToEvent(Long eventId, Long artistId){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(()-> new EventNotFoundException("Event not Found with id: " + eventId));
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(()-> new ArtistNotFoundException("Artist not Found with id: " + artistId));
        EventArtist eventArtist = new EventArtist();
        eventArtist.setArtist(artist);
        eventArtist.setEvent(event);
        EventArtist createdEventArtist = eventArtistRepository.save(eventArtist);
    }
}
