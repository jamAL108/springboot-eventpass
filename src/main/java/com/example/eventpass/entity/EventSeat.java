package com.example.eventpass.entity;

import com.example.eventpass.entity.enums.EventSeatStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name="EventSeat",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"event_id","seat_id"})
        }
)
public class EventSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="seat_id", nullable = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private EventSeatStatus status;
}
