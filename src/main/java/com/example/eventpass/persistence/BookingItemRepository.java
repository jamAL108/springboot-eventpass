package com.example.eventpass.persistence;

import com.example.eventpass.entity.BookingItem;
import com.example.eventpass.entity.dto.EventSeatResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingItemRepository extends JpaRepository<BookingItem,Long> {

    @Query("""
    SELECT new com.example.eventpass.entity.dto.EventSeatResponse(
        es.id,
        s.seatNumber,
        es.status
    )
    FROM BookingItem bi
    JOIN bi.eventSeat es
    JOIN es.seat s
    WHERE bi.booking.id = :bookingId
    """)
    List<EventSeatResponse> findSeatsByBookingId(
            @Param("bookingId") Long bookingId);
}
