package com.sapient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sapient.model.Movie;
import com.sapient.repository.MovieRepository;
import com.sapient.service.MovieService;

/**
 * @author Arun Khurana
 *
 */
public class MovieServiceTest {

	@Mock
	private MovieRepository movieRepository;

	@InjectMocks
	private MovieService movieService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetMovieByTitle() {
		String title = "Test Movie";
		Movie sampleMovie = new Movie();
		sampleMovie.setTitle(title);

		when(movieRepository.findByTitle(title)).thenReturn(sampleMovie);

		Movie result = movieService.getMovieByTitle(title);

		assertNotNull(result);
		assertEquals(title, result.getTitle());
	}

	@Test
	public void testCreateMovie() {
		Movie testMovie = new Movie();
		testMovie.setTitle("Test Movie");

		Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(testMovie);
		Movie movie = movieService.createMovie(testMovie);

		assertNotNull(movie);
		assertEquals(testMovie.getTitle(), movie.getTitle());
	}

	@Test
	public void testUpdateMovie() {
		Long id = 1L;
		Movie movie = new Movie();
		movie.setMovie_id(id);

		when(movieRepository.existsById(id)).thenReturn(true);
		Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);
		Movie updatedMovie = new Movie();
		updatedMovie.setTitle("Updated Movie");

		movieService.updateMovie(id, updatedMovie);

		assertEquals(id, updatedMovie.getMovie_id());
		assertEquals("Updated Movie", updatedMovie.getTitle());
	}

	@Test
	public void testDeleteMovie() {
		Long id = 1L;

		movieService.deleteMovie(id);

		verify(movieRepository, times(1)).deleteById(id);
	}
}
