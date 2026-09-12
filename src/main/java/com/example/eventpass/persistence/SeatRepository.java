package com.example.eventpass.persistence;

import com.example.eventpass.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> getAllSeatByVenueId(Long venueId);
}
