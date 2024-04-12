package com.sapient;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sapient.model.Movie;
import com.sapient.model.Show;
import com.sapient.model.Theatre;
import com.sapient.repository.MovieRepository;
import com.sapient.repository.ShowRepository;
import com.sapient.repository.TheatreRepository;
import com.sapient.service.ShowService;

public class ShowServiceTest {

	@Mock
	private ShowRepository showRepository;

	@Mock
	private TheatreRepository theatreRepository;

	@Mock
	private MovieRepository movieRepository;

	@InjectMocks
	private ShowService showService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetShowsByDate() {
		LocalDateTime dateTime = LocalDateTime.now();
		List<Show> expectedShows = new ArrayList<>();
		when(showRepository.findByDateTime(dateTime)).thenReturn(expectedShows);

		List<Show> result = showService.getShowsByDate(dateTime);

		assertNotNull(result);
		assertEquals(expectedShows, result);
	}

	@Test
	public void testCreateShow() {
		Theatre existingTheatre = new Theatre();
		existingTheatre.setTheatre_id(1L);
		Movie existingMovie = new Movie();
		existingMovie.setMovie_id(2L);

		Show showToCreate = new Show();
		showToCreate.setTheatre(existingTheatre);
		showToCreate.setMovie(existingMovie);

		when(theatreRepository.findById(existingTheatre.getTheatre_id())).thenReturn(Optional.of(existingTheatre));
		when(movieRepository.findById(existingMovie.getMovie_id())).thenReturn(Optional.of(existingMovie));
		when(showRepository.save(showToCreate)).thenReturn(showToCreate);

		Show createdShow = showService.createShow(showToCreate);

		assertNotNull(createdShow);
		assertEquals(existingTheatre, createdShow.getTheatre());
		assertEquals(existingMovie, createdShow.getMovie());
	}

	@Test
	public void testUpdateShow() {
		Long showId = 1L;
		Show existingShow = new Show();
		existingShow.setShow_id(showId);

		when(showRepository.findById(showId)).thenReturn(Optional.of(existingShow));
		when(showRepository.save(existingShow)).thenReturn(existingShow);

		Show updatedShow = showService.updateShow(showId, existingShow);

		assertNotNull(updatedShow);
		assertEquals(showId, updatedShow.getShow_id());
	}

	@Test
	public void testDeleteShow() {
		Long showId = 1L;

		when(showRepository.findById(showId)).thenReturn(Optional.of(new Show()));

		assertDoesNotThrow(() -> showService.deleteShow(showId));
	}

	@Test
	public void testGetAllShows() {
		List<Show> expectedShows = new ArrayList<>();
		when(showRepository.findAll()).thenReturn(expectedShows);

		List<Show> result = showService.getAllShows();

		assertNotNull(result);
		assertEquals(expectedShows, result);
	}
}
