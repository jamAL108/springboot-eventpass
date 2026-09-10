package com.example.eventpass.entity.dto.venue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VenueResponse {

    private Long id;
    private String name;
    private String address;
}
