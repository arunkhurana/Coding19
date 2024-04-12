package com.sapient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.model.Movie;

/**
 * Repository for handling movies in the database.
 *
 * @author Arun Khurana
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	Movie findByTitle(String title);

}