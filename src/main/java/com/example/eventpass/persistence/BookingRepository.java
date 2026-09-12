package com.example.eventpass.persistence;

import com.example.eventpass.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("""
    SELECT b
    FROM Booking b
    JOIN FETCH b.event
    WHERE b.id = :id
    """)
    Optional<Booking> findWithEventById(@Param("id") Long id);
}
