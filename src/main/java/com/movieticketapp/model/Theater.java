package com.movieticketapp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "theater")
public class Theater {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", nullable = false, updatable = false)
	private Long id;
	   public Theater(Long id, String name, String location, int totalSeats, int availableSeats) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
	}
	private String name; 
	   private String location; 
	   private int totalSeats; 
	   private int availableSeats;
	   public Theater() {
	        // No-arg constructor
	    }
	   
	   //without mappedBy separate table
	   @OneToMany(mappedBy = "theater") //theater field in Movie table is the "owner" of the relationship, and Theater just keeps a list of the movies without directly controlling the relationship
	    private List<Movie> movies;
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	@Override
	public String toString() {
		return "Theater [id=" + id + ", name=" + name + ", location=" + location + ", totalSeats=" + totalSeats
				+ ", availableSeats=" + availableSeats + "]";
	} 

}
