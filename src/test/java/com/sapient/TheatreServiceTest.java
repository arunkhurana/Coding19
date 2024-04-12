package com.sapient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sapient.model.Theatre;
import com.sapient.repository.TheatreRepository;
import com.sapient.service.TheatreService;

/**
 * 
 * @author Arun Khurana
 *
 */
public class TheatreServiceTest {

	@Mock
	private TheatreRepository theatreRepository;

	@InjectMocks
	private TheatreService theatreService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetTheatresByTown() {
		String town = "Gurgaon";
		Theatre theatre1 = new Theatre();
		theatre1.setTheatre_id(1L);
		theatre1.setTown(town);
		Theatre theatre2 = new Theatre();
		theatre2.setTheatre_id(2L);
		theatre2.setTown(town);

		List<Theatre> theatresInTown = new ArrayList<>();
		theatresInTown.add(theatre1);
		theatresInTown.add(theatre2);

		when(theatreRepository.findByTown(town)).thenReturn(theatresInTown);

		List<Theatre> result = theatreService.getTheatresByTown(town);

		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(town, result.get(0).getTown());
	}

	@Test
	public void testCreateTheatre() {
		Theatre theatre = new Theatre();
		theatre.setTheatre_id(1L);

		Mockito.when(theatreRepository.save(Mockito.any(Theatre.class))).thenReturn(theatre);

		Theatre createdTheatre = theatreService.createTheatre(theatre);

		assertEquals(1L, createdTheatre.getTheatre_id());
	}

	@Test
	public void testUpdateTheatre() {
		Long id = 1L;
		Theatre existingTheatre = new Theatre();
		existingTheatre.setTheatre_id(id);

		when(theatreRepository.existsById(id)).thenReturn(true);
		Mockito.when(theatreRepository.save(Mockito.any(Theatre.class))).thenReturn(existingTheatre);

		Theatre updatedTheatre = new Theatre();
		updatedTheatre.setTheatre_id(id);

		theatreService.updateTheatre(id, updatedTheatre);

		assertEquals(id, updatedTheatre.getTheatre_id());
	}

	@Test
	public void testDeleteTheatre() {
		Long id = 1L;

		theatreService.deleteTheatre(id);

		verify(theatreRepository, times(1)).deleteById(id);
	}
}