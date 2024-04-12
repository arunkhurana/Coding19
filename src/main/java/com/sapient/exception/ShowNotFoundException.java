package com.sapient.exception;

/**
 * Custom exception class 
 * 
 * @author Arun Khurana
 */
public class ShowNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 *
	 * @param showId The ID of the show that was not found.
	 */
	public ShowNotFoundException(String showId) {
		super("Show not found with ID: " + showId);
	}
}
