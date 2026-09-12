package com.example.eventpass.entity.dto.booking;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateBookingRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long eventId;

    @NotEmpty
    private List<Long> eventSeatIds;
}
