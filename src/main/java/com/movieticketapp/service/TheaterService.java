package com.movieticketapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
            List<Movie> runningMovies = movieRepository.findByTheaterId(theater.getId())
            		.stream()
            		.map(movie -> new Movie(movie.getId(),movie.getTitle(),movie.getGenre()))
            		.toList();
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

	public Theater addTheater(Theater theater) {
		
		return theaterRepository.save(theater);
	}

	public void deleteTheater(Long id) {
		theaterRepository.deleteById(id);
		
	}


	 public Theater updateTheater(Long theaterId, Theater updatedTheater) {
        Theater existingTheater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater with ID " + theaterId + " does not exist."));
        
        existingTheater.setName(updatedTheater.getName());
        existingTheater.setLocation(updatedTheater.getLocation());
        existingTheater.setTotalSeats(updatedTheater.getTotalSeats());
        existingTheater.setAvailableSeats(updatedTheater.getAvailableSeats());

        return theaterRepository.save(existingTheater);
    }

	public List<Theater> getAllTheaters() {
	
		return theaterRepository.findAll();
	}

	public Theater getTheaterById(Long Id) {
		// TODO Auto-generated method stub
		return theaterRepository.findById(Id)
				.orElseThrow(()-> new RuntimeException("Theater not found with id: " + Id));
	}

	

	

	}


