package com.example.eventpass.entity.dto.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventResponse {

    private Long id;
    private String name;
    private LocalDate eventDate;
    private Long venueId;
    private String venueName;
}
