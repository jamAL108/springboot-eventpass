package com.example.eventpass.entity.dto.booking;

import com.example.eventpass.entity.dto.EventSeatResponse;
import com.example.eventpass.entity.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private Long id;
    private Long userId;
    private Long eventId;
    private String eventName;
    private LocalDate eventDate;
    private BookingStatus status;
    private LocalDateTime createdAt;
    private List<EventSeatResponse> bookedSeats;
}
