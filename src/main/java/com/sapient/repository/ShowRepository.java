package com.sapient.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.model.Movie;
import com.sapient.model.Show;

/**
 * Repository for handling theatre shows in the database.
 *
 * @author Arun Khurana
 *
 */
public interface ShowRepository extends JpaRepository<Show, Long> {

	List<Show> findByDateTime(LocalDateTime showTime);

	List<Show> findByMovieAndDateTimeBetween(Movie movie, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
