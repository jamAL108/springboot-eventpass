package com.example.eventpass.service;

import com.example.eventpass.entity.dto.EventSeatResponse;
import com.example.eventpass.entity.dto.artist.ArtistResponse;
import com.example.eventpass.entity.dto.event.CreateEventRequest;
import com.example.eventpass.entity.dto.event.EventResponse;

import java.util.List;

public interface EventService {

    EventResponse findById(Long id);
    List<EventResponse> getAllEvents();
    EventResponse addEvent(CreateEventRequest request);
    List<EventSeatResponse> getSeatsByEventId(Long id);
    List<ArtistResponse> getArtistsByEventId(Long id);
    void addArtistToEvent(Long eventId, Long artistId);
}
