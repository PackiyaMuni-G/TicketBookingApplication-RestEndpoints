package com.movieticketapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	   @GetMapping("/getAllTheaters")
	    public ResponseEntity<List<Theater>> getAllTheaters() {
	        List<Theater> theaters = theaterService.getAllTheaters();
	        return ResponseEntity.ok(theaters);
	    }
	//It ensures the response will be json(by default it json,but it not mandatory
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
	 
	 @PostMapping(value = "/addTheater", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Theater> addTheater(@RequestBody Theater theater) {
		  try {
	            Theater savedTheater = theaterService.addTheater(theater);
	            return ResponseEntity.ok(savedTheater);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.badRequest().build();
	        }
	    }
	 @DeleteMapping("/deleteTheater/{id}")
	 public ResponseEntity<String> deleteTheater(@PathVariable Long id) {
	     try {
	         theaterService.deleteTheater(id);
	         return ResponseEntity.ok("Theater with ID " + id + " deleted successfully.");
	     } catch (RuntimeException e) {
	         return ResponseEntity.status(404).body(e.getMessage());
	     }
	 }
	 
	 @PutMapping("/updateTheater/{theaterId}")
	    public ResponseEntity<Theater> updateTheater(@PathVariable Long theaterId, 
	            @RequestBody Theater updatedTheater) {
	        Theater updatedEntity = theaterService.updateTheater(theaterId, updatedTheater);
	        return ResponseEntity.ok(updatedEntity);
	    }


	 
	 

}
