package com.example.eventpass.service.Impl;

import com.example.eventpass.entity.*;
import com.example.eventpass.entity.dto.EventSeatResponse;
import com.example.eventpass.entity.dto.booking.BookingResponse;
import com.example.eventpass.entity.dto.booking.CreateBookingRequest;
import com.example.eventpass.entity.enums.BookingStatus;
import com.example.eventpass.entity.enums.EventSeatStatus;
import com.example.eventpass.exceptions.BookingNotFoundException;
import com.example.eventpass.exceptions.EventNotFoundException;
import com.example.eventpass.exceptions.UserNotFoundException;
import com.example.eventpass.persistence.*;
import com.example.eventpass.service.BookingService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final AppUserRepository appUserRepository;
    private final EventRepository eventRepository;
    private final EventSeatRepository eventSeatRepository;
    private final BookingItemRepository bookingItemRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BookingResponse bookSeate(CreateBookingRequest request){
        AppUser user = appUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + request.getEventId()));

        if(event.getEventDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot book tickets for past events");
        }

        List<Long> seatids = request.getEventSeatIds();

        if(seatids==null || seatids.size()==0 || seatids.contains(null)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect Seats selected");
        }

        if(new HashSet<>(seatids).size() != seatids.size()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate seats selected");
        }


        List<EventSeat> eventSeats = eventSeatRepository.findAllBySeatIds(seatids);

        if(seatids.size() != eventSeats.size()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seats doesnt exist");
        }

        for(EventSeat eventSeat : eventSeats){

            if(!eventSeat.getEvent().getId().equals(event.getId())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Seats doesnt exist for Event id: " + event.getId());
            }

            if(!eventSeat.getStatus().equals(EventSeatStatus.AVAILABLE)){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Seats not already booked");
            }
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setEvent(event);
        booking.setStatus(BookingStatus.CONFIRMED);

        Booking createdBooking = bookingRepository.save(booking);

        List<BookingItem> items = new ArrayList<>();
        List<EventSeatResponse> eventSeatResponses = new ArrayList<>();

        for(EventSeat eventSeat : eventSeats){
            BookingItem bookingItem = new BookingItem();
            bookingItem.setBooking(createdBooking);
            bookingItem.setEventSeat(eventSeat);
            eventSeat.setStatus(EventSeatStatus.BOOKED);
            eventSeatResponses.add(modelMapper.map(eventSeat,EventSeatResponse.class));
            items.add(bookingItem);
        }

        bookingItemRepository.saveAll(items);

        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setId(booking.getId());
        bookingResponse.setEventId(event.getId());
        bookingResponse.setUserId(user.getId());
        bookingResponse.setStatus(BookingStatus.CONFIRMED);
        bookingResponse.setEventDate(event.getEventDate());
        bookingResponse.setEventName(event.getName());
        bookingResponse.setCreatedAt(booking.getCreatedAt());
        bookingResponse.setBookedSeats(eventSeatResponses);

        return bookingResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findWithEventById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found with id: " + id));

        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setEventId(booking.getEvent().getId());
        response.setUserId(booking.getUser().getId());
        response.setEventName(booking.getEvent().getName());
        response.setEventDate(booking.getEvent().getEventDate());
        response.setStatus(booking.getStatus());
        response.setCreatedAt(booking.getCreatedAt());
        response.setBookedSeats(bookingItemRepository.findSeatsByBookingId(id));

        return response;
    }
}
