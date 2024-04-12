package com.sapient.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Represents a movie in the ticketing system.
 * 
 * @author Arun Khurana
 *
 */
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;
    
    private String title;
    
    private String genre;
    
    private Long duration;

	/**
	 * @return the movie_id
	 */
	public Long getMovie_id() {
		return movie_id;
	}

	/**
	 * @param movie_id the movie_id to set
	 */
	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}

	/**
	 * @return the title
	 */
	@NotBlank(message = "Title is required")
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the duration
	 */
	public Long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Movie [movie_id=" + movie_id + ", title=" + title + ", genre=" + genre + ", duration=" + duration + "]";
	}
}
