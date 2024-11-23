package com.movieticketapp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieticketapp.model.Booking;
import com.movieticketapp.model.Theater;
import com.movieticketapp.repository.BookingRepository;
import jakarta.transaction.Transactional;

@Service
public class BookingService {
	
	@Autowired
	private TheaterService theaterService;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
      private MovieService movieService;

	
	//When the bookTickets method starts, Spring begins a database transaction.
	//If any exception is thrown during the execution of the method, the transaction is rolled back, maintaining the consistency
	//It maintain a data consistency,reliablity(trust)
	@Transactional
	    public Booking bookTickets(Long theaterId, Long movieId, int numberOfSeats) {
	        Theater theater = theaterService.getTheaterById(theaterId);
	        
	        // Check if enough seats are available
	        if (theater.getAvailableSeats() < numberOfSeats) {
	            throw new RuntimeException("Not enough seats available. Only " + 
	                theater.getAvailableSeats() + " seats left. Please try another theater or movie.");
	        }
	        
	        // Create booking
	        Booking booking = new Booking();
	        booking.setTheater(theater);
	        booking.setMovie(movieService.getMovieById(movieId));
	        booking.setNumberOfSeats(numberOfSeats);
	        booking.setBookingTime(LocalDateTime.now());
	        booking.setStatus("CONFIRMED");
	        
	        // Update available seats
	        theater.setAvailableSeats(theater.getAvailableSeats() - numberOfSeats);
	        theaterService.updateTheater(theaterId, theater);
	        //If bookingRepository.save(booking) fails, the update to theater will also be rolled back.
	        return bookingRepository.save(booking);
	    }
	
}
