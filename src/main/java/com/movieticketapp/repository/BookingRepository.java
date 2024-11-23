package com.movieticketapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticketapp.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
