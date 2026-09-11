package com.example.eventpass.persistence;

import com.example.eventpass.entity.Seat;
import com.example.eventpass.entity.dto.seat.VenueSeatResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> getAllSeatByVenueId(Long venueId);
}
