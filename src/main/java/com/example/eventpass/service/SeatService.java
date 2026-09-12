package com.example.eventpass.service;

import com.example.eventpass.entity.dto.seat.CreateSeatRequest;
import com.example.eventpass.entity.dto.seat.VenueSeatResponse;

import java.util.List;

public interface SeatService {

    List<VenueSeatResponse> getAllSeats(Long venueId);
    VenueSeatResponse addSeat(Long venueId, CreateSeatRequest createSeatRequest);
}
