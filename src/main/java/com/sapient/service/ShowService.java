package com.sapient.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.exception.MovieNotFoundException;
import com.sapient.exception.ShowNotFoundException;
import com.sapient.exception.TheatreNotFoundException;
import com.sapient.model.Movie;
import com.sapient.model.Show;
import com.sapient.model.Theatre;
import com.sapient.repository.MovieRepository;
import com.sapient.repository.ShowRepository;
import com.sapient.repository.TheatreRepository;

/**
 * Service class for handling shows related business logic.
 * 
 * @author Arun Khurana
 */
@Service
public class ShowService {

	private static final Logger logger = LoggerFactory.getLogger(ShowService.class);

	private final ShowRepository showRepository;
	private TheatreRepository theatreRepository;
	private MovieRepository movieRepository;

	@Autowired
	public ShowService(ShowRepository showRepository, TheatreRepository theatreRepository,
			MovieRepository movieRepository) {
		this.showRepository = showRepository;
		this.theatreRepository = theatreRepository;
		this.movieRepository = movieRepository;
	}

	/**
	 * Get a list of shows for a given movie and show time
	 *
	 * @param date The movie and date/time for which shows are requested.
	 * @return A list of shows for the specified movie and date/time.
	 */
	public List<Show> getShowsByMovieAndDate(Movie movie, LocalDateTime dateTime) {
		LocalDateTime startDateTime = dateTime.with(LocalTime.MIN);
		LocalDateTime endDateTime = dateTime.with(LocalTime.MAX);

		return showRepository.findByMovieAndDateTimeBetween(movie, startDateTime, endDateTime);
	}

	/**
	 * Get a list of shows for a given date and time
	 *
	 * @param date The date/time for which shows are requested.
	 * @return A list of shows for the specified date/time.
	 */
	@Transactional(readOnly = true)
	public List<Show> getShowsByDate(LocalDateTime dateTime) {
		try {
			return showRepository.findByDateTime(dateTime);
		} catch (Exception e) {
			logger.error("Show not found for date:  " + dateTime);
			throw new ShowNotFoundException("Show for given date" + dateTime + " not found.");
		}
	}

	/**
	 * Create a new show.
	 *
	 * @param show The show object to be created.
	 * @return The created show.
	 */
	@Transactional
	public Show createShow(Show show) {
		try {
			// Fetch the theatre by id
			Theatre existingTheatre = theatreRepository.findById(show.getTheatre().getTheatre_id())
					.orElseThrow(() -> new TheatreNotFoundException("Theatre not found"));
			show.setTheatre(existingTheatre);

			// Fetch the movie by id
			Movie existingMovie = movieRepository.findById(show.getMovie().getMovie_id())
					.orElseThrow(() -> new MovieNotFoundException("Movie not found"));
			show.setTheatre(existingTheatre);
			show.setMovie(existingMovie);
			return showRepository.save(show);
		} catch (Exception e) {
			logger.error("Error occurred while creating a new show: " + e.getMessage(), e);
			throw new ShowNotFoundException("Error while creating a new show");
		}
	}

	/**
	 * Update an existing show.
	 *
	 * @param id   The ID of the show to be updated.
	 * @param show The updated show object.
	 * @return The updated show.
	 * @throws ShowNotFoundException if the show with the given ID does not exist.
	 */
	@Transactional
	public Show updateShow(Long id, Show show) {
		logger.info("Update show for id:" + id);

		try {
			Optional<Show> existingShowOptional = showRepository.findById(id);

			if (existingShowOptional.isPresent()) {
				Show existingShow = existingShowOptional.get();
				// Update the properties of the existing show with the new values
				existingShow.setDateTime(show.getDateTime());
				existingShow.setTheatre(show.getTheatre());
				existingShow.setMovie(show.getMovie());

				return showRepository.save(existingShow);
			}
		} catch (Exception e) {
			logger.error("Error occurred while updating the show: " + id);
			throw new ShowNotFoundException("Show with ID " + id + " not updated.");
		}

		return null;
	}

	/**
	 * Delete a show by its ID.
	 *
	 * @param id The ID of the show to be deleted.
	 * @throws ShowNotFoundException if the show with the given ID does not exist.
	 */
	@Transactional
	public void deleteShow(Long id) {
		Optional<Show> showOptional = showRepository.findById(id);

		if (showOptional.isPresent()) {
			showRepository.deleteById(id);
		} else {
			logger.error("Error occurred while deleting a show: " + id);
			throw new ShowNotFoundException("Show with ID " + id + " not found.");
		}
	}

	/**
	 * Get a list of all shows in the system.
	 *
	 * @return List of all shows.
	 */
	public List<Show> getAllShows() {
		return showRepository.findAll();
	}
}
