package com.example.eventpass.controller;

import com.example.eventpass.entity.Seat;
import com.example.eventpass.entity.dto.seat.CreateSeatRequest;
import com.example.eventpass.entity.dto.seat.VenueSeatResponse;
import com.example.eventpass.service.SeatService;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venue/{venueId}/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping()
    public ResponseEntity<List<VenueSeatResponse>> getAllSeats(@PathVariable Long venueId){
        List<VenueSeatResponse> venueSeats = seatService.getAllSeats(venueId);
        return ResponseEntity.ok(venueSeats);
    }

    @PostMapping()
    public ResponseEntity<VenueSeatResponse> addSeat(@PathVariable Long venueId, @Valid @RequestBody CreateSeatRequest request){
        VenueSeatResponse seat = seatService.addSeat(venueId,request);
        return ResponseEntity.status(201).body(seat);
    }
}
