package com.sapient.exception;

/**
 * Custom exception class
 * 
 * @author Arun Khurana
 */

public class TheatreNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 *
	 * @param showId The ID of the show that was not found.
	 */
	public TheatreNotFoundException(String theatreId) {
		super("Theatre not found with ID: " + theatreId);
	}
}
