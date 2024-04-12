package com.sapient.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Represents a show in the ticketing system.
 * 
 * @author Arun Khurana
 *
 */
@Entity
public class Show {
    
	/**
     * Unique identifier for the show.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long show_id;

    /**
     * Date of the show.
     */
    private LocalDateTime dateTime;

    /**
     * Theatre where the show is performed.
     */
    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;
    
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

	/**
	 * @return the show_id
	 */
	public Long getShow_id() {
		return show_id;
	}

	/**
	 * @param show_id the show_id to set
	 */
	public void setShow_id(Long show_id) {
		this.show_id = show_id;
	}

	/**
	 * @return the dateTime
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the theatre
	 */
	public Theatre getTheatre() {
		return theatre;
	}

	/**
	 * @param theatre the theatre to set
	 */
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @param movie the movie to set
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "Show [show_id=" + show_id + ", dateTime=" + dateTime + ", theatre=" + theatre + ", movie=" + movie
				+ "]";
	}

}
