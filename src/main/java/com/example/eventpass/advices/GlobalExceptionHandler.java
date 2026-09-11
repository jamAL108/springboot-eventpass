package com.example.eventpass.advices;

import com.example.eventpass.exceptions.EventNotFoundException;
import com.example.eventpass.exceptions.VenueNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VenueNotFoundException.class)
    public ResponseEntity<String> venueNotFound(VenueNotFoundException ex){
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> eventNotFound(EventNotFoundException ex){
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }
}
