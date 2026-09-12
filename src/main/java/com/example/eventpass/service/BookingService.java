package com.example.eventpass.service;


import com.example.eventpass.entity.dto.booking.BookingResponse;
import com.example.eventpass.entity.dto.booking.CreateBookingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface BookingService {

    BookingResponse bookSeate(CreateBookingRequest reauest);
    BookingResponse getBookingById(Long id);
}
