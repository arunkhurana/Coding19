package com.sapient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.model.Movie;
import com.sapient.service.MovieService;

/**
 * Controller class for managing movies.
 * 
 * @author Arun Khurana
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	/**
	 * Get a Movie by its title.
	 *
	 * @param id The title of the movie.
	 * @return The Movie if found, otherwise not found.
	 */
	@GetMapping("/byTitle/{title}")
	public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title) {

		try {
			Movie movie = movieService.getMovieByTitle(title);
			return ResponseEntity.ok(movie);
		} catch (Exception e) {
			logger.error("Error occurred while fetching the movie : " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Create a new Movie.
	 *
	 * @param Movie The Movie to create.
	 * @return A response with a status code indicating success or failure.
	 */
	@PostMapping
	public ResponseEntity<Movie> createMovie(@RequestBody Movie Movie) {
		try {
			Movie createdMovie = movieService.createMovie(Movie);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
		} catch (Exception e) {
			logger.error("Error occurred while creating movie: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Update an existing Movie by its unique id.
	 *
	 * @param id           The unique id of the Movie to update.
	 * @param updatedMovie The updated Movie object.
	 * @return A response with a status code indicating success or failure.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
		try {
			movieService.updateMovie(id, updatedMovie);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error("Error occurred while updating movie: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Delete a Movie by its unique id.
	 *
	 * @param id The id of the Movie to delete.
	 * @return A response with a status code indicating success or failure.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
		try {
			movieService.deleteMovie(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error("Error occurred while deleting movie: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
