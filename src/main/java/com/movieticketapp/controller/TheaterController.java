package com.movieticketapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieticketapp.model.Theater;
import com.movieticketapp.model.TheaterDetailsDTO;
import com.movieticketapp.service.TheaterService;

@RestController
@RequestMapping("/bookmyshow")
public class TheaterController {
	
	@Autowired
	private TheaterService theaterService;	
	
	 @GetMapping(value = "/{location}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Theater>> getTheatersByLocation(@PathVariable String location) {
	        List<Theater> theaters = theaterService.getTheatersByLocation(location);
	        return ResponseEntity.ok(theaters);
	    }
	 @GetMapping("/{location}/{theaterName}")
	    public TheaterDetailsDTO getTheaterDetails(@PathVariable String location, 
	                                             @PathVariable String theaterName) {
	        return theaterService.getTheaterDetails(location, theaterName);
	    }

}
