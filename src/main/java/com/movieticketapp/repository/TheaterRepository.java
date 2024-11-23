package com.movieticketapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticketapp.model.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Long>{

	List<Theater> findByLocation(String location);

	Theater findByLocationAndNameIgnoreCase(String location, String theaterName);



}
