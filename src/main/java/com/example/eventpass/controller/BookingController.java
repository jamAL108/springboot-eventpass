package com.example.eventpass.controller;


import com.example.eventpass.entity.dto.booking.BookingResponse;
import com.example.eventpass.entity.dto.booking.CreateBookingRequest;
import com.example.eventpass.service.Impl.BookingServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingServiceImpl bookingService;

    @PostMapping()
    public ResponseEntity<BookingResponse> bookSeats(@Valid @RequestBody CreateBookingRequest request){
        BookingResponse bookingInformation = bookingService.bookSeate(request);
        return ResponseEntity.status(201).body(bookingInformation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookings(@PathVariable Long id){
        BookingResponse bookingInformation = bookingService.getBookingById(id);
        return ResponseEntity.ok(bookingInformation);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id){
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }
}
