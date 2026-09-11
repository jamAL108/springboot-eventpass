package com.example.eventpass.persistence;

import com.example.eventpass.entity.EventSeat;
import com.example.eventpass.entity.dto.EventSeatResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventSeatRepository extends JpaRepository<EventSeat,Long> {

    @Query("""
    SELECT new com.example.eventpass.entity.dto.EventSeatResponse(
        es.id,
        s.seatNumber,
        es.status
    )
    FROM EventSeat es
    JOIN es.seat s
    WHERE es.event.id = :eventId
    """)
    List<EventSeatResponse> findSeatsByEventId(
            @Param("eventId") Long eventId);
}
