package com.movieticketapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.movieticketapp.model.Booking;
import com.movieticketapp.service.BookingService;

@RestController
@RequestMapping("/bookmyshow/bookings")
public class BookingController {
  
	@Autowired
	private BookingService bookingService;
    
    @PostMapping("/book")
    public ResponseEntity<?> bookTickets(
            @RequestParam Long theaterId,
            @RequestParam Long movieId,
            @RequestParam int numberOfSeats) {
        try {
            Booking booking = bookingService.bookTickets(theaterId, movieId, numberOfSeats);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}