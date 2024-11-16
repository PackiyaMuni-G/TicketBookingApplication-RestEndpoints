package com.movieticketapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieticketapp.model.Movie;
import com.movieticketapp.model.Theater;
import com.movieticketapp.model.TheaterDetailsDTO;
import com.movieticketapp.repository.MovieRepository;
import com.movieticketapp.repository.TheaterRepository;

@Service
public class TheaterService {
	
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	@Autowired
	private MovieRepository movieRepository;

	public List<Theater> getTheatersByLocation(String location) {
	
		return theaterRepository.findByLocation(location);
	}

	public TheaterDetailsDTO getTheaterDetails(String location, String theaterName) {
        Theater theater = theaterRepository.findByLocationAndNameIgnoreCase(location, theaterName);
        if (theater != null) {
            List<Movie> runningMovies = movieRepository.findByTheaterId(theater.getId());
            return new TheaterDetailsDTO(
                theater.getId(),
                theater.getName(),
                theater.getLocation(),
                theater.getTotalSeats(),
                theater.getAvailableSeats(),
                runningMovies
            );
        }
        return null;
    }
	}


