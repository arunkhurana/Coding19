package com.sapient.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.model.Theatre;
import com.sapient.repository.TheatreRepository;

/**
 * Service class for handling theatres related business logic.
 *
 * @author Arun Khurana
 *
 */
@Service
public class TheatreService {
	
	private static final Logger logger = LoggerFactory.getLogger(TheatreService.class);
	
	private final TheatreRepository theatreRepository;

	@Autowired
	public TheatreService(TheatreRepository theatreRepository) {
		this.theatreRepository = theatreRepository;
	}

	/**
	 * Get a list of all theatres by town
	 *
	 * @return List of all theatres.
	 */
	public List<Theatre> getTheatresByTown(String town) {
		return theatreRepository.findByTown(town);
	}

	/**
	 * Get a list of all theatres in the system.
	 *
	 * @return List of all theatres.
	 */
	public List<Theatre> getAllTheatres() {
		return theatreRepository.findAll();
	}

	/**
	 * Get a theatre by its unique identifier.
	 *
	 * @param id The identifier of the theatre.
	 * @return The theatre if found, otherwise empty.
	 */
	public Optional<Theatre> getTheatreById(Long id) {
		return theatreRepository.findById(id);
	}

	/**
	 * Create a new theatre.
	 *
	 * @param theatre The theatre to create.
	 * @return The created theatre.
	 */
	public Theatre createTheatre(Theatre theatre) {
		return theatreRepository.save(theatre);
	}

	/**
	 * Update an existing theatre.
	 *
	 * @param id             The identifier of the theatre to update.
	 * @param updatedTheatre The updated theatre data.
	 */
	public void updateTheatre(Long id, Theatre updatedTheatre) {
		if (theatreRepository.existsById(id)) {
			updatedTheatre.setTheatre_id(id);
			theatreRepository.save(updatedTheatre);
		}
		logger.error("Theatre Updated with Id: " + id);
	}

	/**
	 * Delete a theatre by its unique identifier.
	 *
	 * @param id The identifier of the theatre to delete.
	 */
	public void deleteTheatre(Long id) {
		theatreRepository.deleteById(id);
	}
}
