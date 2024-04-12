package com.sapient.controller;

import java.time.LocalDateTime;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.sapient.model.Show;
import com.sapient.service.ShowService;

/**
 * Controller class for managing shows.
 * 
 * @author Arun Khurana
 */

@RestController
@RequestMapping("/shows")
public class ShowController {

	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);

	@Autowired
	private ShowService showService;

	/**
	 * Create a new show.
	 *
	 * @param show The show object to be created.
	 * @return A response with a status code indicating success or failure.
	 */
	@PostMapping
	public ResponseEntity<Show> createShow(@RequestBody Show show) {
		try {
			Show createShow = showService.createShow(show);
			return ResponseEntity.status(HttpStatus.CREATED).body(createShow);
		} catch (Exception e) {
			logger.error("Error occurred while creating a show: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Update an existing show.
	 *
	 * @param id   The ID of the show to be updated.
	 * @param show The updated show object.
	 * @return A response with a status code indicating success or failure.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Show> updateShow(@PathVariable("id") Long id, @RequestBody Show show) {
		try {
			Show updatedShow = showService.updateShow(id, show);
			if (updatedShow != null) {
				return ResponseEntity.ok(updatedShow);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			logger.error("Error occurred while updating a show: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Delete a show by its ID.
	 *
	 * @param id The ID of the show to be deleted.
	 * @return A response with a status code indicating success or failure.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteShow(@PathVariable("id") Long id) {
		try {
			showService.deleteShow(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			logger.error("Error occurred while deleting a show: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Get a list of shows for a given date.
	 *
	 * @param date The date for which shows are requested.
	 * @return A response with a status code indicating success or failure.
	 */
	@GetMapping("/{date}")
	public ResponseEntity<List<Show>> getShowsByDate(
			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

		try {
			List<Show> shows = showService.getShowsByDate(date);
			return ResponseEntity.ok(shows);
		} catch (Exception e) {
			logger.error("Error occurred while fetching shows by date :" + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Get a list of all shows.
	 *
	 * @return A response with a status code indicating success or failure.
	 */
	@GetMapping
	public ResponseEntity<List<Show>> getAllShows() {
		try {
			List<Show> shows = showService.getAllShows();
			return ResponseEntity.ok(shows);
		} catch (Exception e) {
			logger.error("Error occurred while fetching all shows :" + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
