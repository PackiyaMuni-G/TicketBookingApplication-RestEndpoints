package com.movieticketapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieticketapp.model.Movie;
import com.movieticketapp.repository.MovieRepository;

@Service
public class MovieService {
 
	@Autowired
	private MovieRepository movieRepository;
	
	 public Movie getMovieById(Long movieId) {
	        return movieRepository.findById(movieId)
	                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));
	    }
}
