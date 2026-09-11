package com.example.eventpass.entity.dto;

import com.example.eventpass.entity.enums.EventSeatStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventSeatResponse {

    private Long id;
    private String seatNumber;
    private EventSeatStatus status;
}
