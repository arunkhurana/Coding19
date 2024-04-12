package com.sapient.exception;

/**
 * Custom exception class 
 * 
 * @author Arun Khurana
 */
public class MovieNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 *
	 * @param movieId The ID of the movie that was not found.
	 */
	public MovieNotFoundException(String movieId) {
		super("Movie not found with ID: " + movieId);
	}
}
