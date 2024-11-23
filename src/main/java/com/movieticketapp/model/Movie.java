package com.movieticketapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	 private Long id;
	private String title;
    private String genre;
    
    @ManyToOne   //Many movies belong to one theater,so we can map theater_id column in movie table from theater table 
   // Ensures that every movie has a valid theater assigned (nullable=false)
    //this theater_id column in movie table point back to the theater it belongs to
    @JoinColumn(name = "theater_id", referencedColumnName = "id", nullable = false) //
    @JsonIgnore //it ensures this field will be ignored during serialization
    private Theater theater;  //it controls the relatonship b/w movie & theater

 // Default constructor 
    public Movie() {
    }

    public Movie(Long id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }


	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}
}
