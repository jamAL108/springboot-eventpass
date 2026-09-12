package com.example.eventpass.advices;

import com.example.eventpass.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BookingNotFoundException.class, VenueNotFoundException.class, EventNotFoundException.class, ArtistNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<String> NotFound(VenueNotFoundException ex){
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }
}
