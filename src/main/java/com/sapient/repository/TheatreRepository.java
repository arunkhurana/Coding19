package com.sapient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.model.Theatre;

/**
 * Repository for handling theatre information in the database.
 *
 * @author Arun Khurana
 *
 */
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

	List<Theatre> findByTown(String town);

}
