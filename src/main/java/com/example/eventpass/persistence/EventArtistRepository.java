package com.example.eventpass.persistence;

import com.example.eventpass.entity.EventArtist;
import com.example.eventpass.entity.dto.artist.ArtistResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventArtistRepository extends JpaRepository<EventArtist,Long> {

    @Query("""
           SELECT new com.example.eventpass.entity.dto.artist.ArtistResponse(
                a.id,
                a.name
               )
               FROM EventArtist as
               JOIN as.artist a
               WHERE as.event.id = :eventId
           """)
    List<ArtistResponse> findArtistsByEventId(@Param("eventId") Long id);
}
