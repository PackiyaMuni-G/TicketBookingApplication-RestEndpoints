package com.movieticketapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticketapp.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByTheaterId(Long id);

}
