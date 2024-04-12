package com.sapient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.model.Movie;
import com.sapient.repository.MovieRepository;

/**
 * Service class for handling movie related business logic.
 *
 * @author Arun Khurana
 *
 */

@Service
public class MovieService {

	private static final Logger logger = LoggerFactory.getLogger(TheatreService.class);

	@Autowired
	private MovieRepository movieRepository;

	/**
	 * Get a movie details by title.
	 *
	 * @return List of all Movies.
	 */
	public Movie getMovieByTitle(String title) {
		return movieRepository.findByTitle(title);
	}

	/**
	 * Create a new Movie.
	 *
	 * @param Movie The Movie to create.
	 * @return The created Movie.
	 */
	public Movie createMovie(Movie Movie) {
		return movieRepository.save(Movie);
	}

	/**
	 * Update an existing Movie.
	 *
	 * @param id           The identifier of the Movie to update.
	 * @param updatedMovie The updated Movie data.
	 */
	public void updateMovie(Long id, Movie updatedMovie) {
		if (movieRepository.existsById(id)) {
			updatedMovie.setMovie_id(id);
			movieRepository.save(updatedMovie);
		}
		logger.error("Movie Updated with Id: " + id);
	}

	/**
	 * Delete a Movie by its unique identifier.
	 *
	 * @param id The identifier of the Movie to delete.
	 */
	public void deleteMovie(Long id) {
		movieRepository.deleteById(id);
	}
}