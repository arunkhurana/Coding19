package com.sapient.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.model.Movie;
import com.sapient.model.Show;
import com.sapient.model.Theatre;
import com.sapient.service.MovieService;
import com.sapient.service.ShowService;
import com.sapient.service.TheatreService;

/**
 * Controller class for handling requests related to theatres.
 * 
 * @author Arun Khurana
 *
 */
@RestController
@RequestMapping("/theatres")
public class TheatreController {

	private static final Logger logger = LoggerFactory.getLogger(TheatreController.class);

	private final TheatreService theatreService;
	private final ShowService showService;
	private final MovieService movieService;

	/*
	 * Constructor
	 */
	@Autowired
	public TheatreController(TheatreService theatreService, ShowService showService, MovieService movieService) {
		this.theatreService = theatreService;
		this.showService = showService;
		this.movieService = movieService;
	}

	/**
	 * List of theatres currently running the show (movie selected) in the town,
	 * including show timing by a chosen date
	 * 
	 * @param movieTitle
	 * @param dateTime
	 * @return A response with a status code indicating success or failure.
	 */
	@GetMapping("/shows")
	public ResponseEntity<List<Show>> getTheatersRunningMovie(@RequestParam("movieTitle") String movieTitle,
			@RequestParam("town") String town,
			@RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
		List<Show> shows = null;
		try {
			Movie movie = movieService.getMovieByTitle(movieTitle);
			// Fetch the list of all shows by movie name and show time
			shows = showService.getShowsByMovieAndDate(movie, dateTime);
			// Filter the shows by theatre location and return
			return ResponseEntity.ok(shows.stream().filter(show -> show.getTheatre().getTown().equalsIgnoreCase(town))
					.collect(Collectors.toList()));
		} catch (Exception e) {
			logger.error("Error occurred while fetching theatre by town : " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Get a list of all theatres by given town
	 *
	 * @return List of all theatres by town
	 */
	@GetMapping("/byTown/{town}")
	public ResponseEntity<List<Theatre>> getTheatresByTown(@PathVariable String town) {
		try {
			List<Theatre> theatres = theatreService.getTheatresByTown(town);
			return ResponseEntity.ok(theatres);
		} catch (Exception e) {
			logger.error("Error occurred while fetching theatre by town : " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Get a list of all theatres.
	 *
	 * @return List of all theatres with with a status code indicating success or failure.
	 */
	@GetMapping
	public ResponseEntity<List<Theatre>> getAllTheatres() {
		try {
			List<Theatre> theatres = theatreService.getAllTheatres();
			return ResponseEntity.ok(theatres);
		} catch (Exception e) {
			logger.error("Error occurred while fetching list of all theatres : " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Get a theatre by its unique identifier.
	 *
	 * @param id The identifier of the theatre.
	 * @return A response with a status code indicating success or failure.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Theatre> getTheatreById(@PathVariable Long id) {
		try {
			Optional<Theatre> theatre = theatreService.getTheatreById(id);
			return theatre.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		} catch (Exception e) {
			logger.error("Error occurred while fetching a theatre by id : " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Create a new theatre.
	 *
	 * @param theatre The theatre to create.
	 * @return The created theatre with a status code of 201 (Created).
	 */
	@PostMapping
	public ResponseEntity<Theatre> createTheatre(@RequestBody Theatre theatre) {
		try {
			Theatre createdTheatre = theatreService.createTheatre(theatre);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdTheatre);
		} catch (Exception e) {
			logger.error("Error occurred while creating a theatre : " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Update an existing theatre by its unique id.
	 *
	 * @param id The id of the theatre to update.
	 * @param updatedTheatre The updated theatre data.
	 * @return A response with a status code indicating success or failure.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateTheatre(@PathVariable Long id, @RequestBody Theatre updatedTheatre) {
		try {
			theatreService.updateTheatre(id, updatedTheatre);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error("Error occurred while updating a theatre : " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Delete a theatre by its unique id.
	 *
	 * @param id The id of the theatre to delete.
	 * @return A response with a status code indicating success or failure.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTheatre(@PathVariable Long id) {
		try {
			theatreService.deleteTheatre(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error("Error occurred while deleting a theatre : " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
