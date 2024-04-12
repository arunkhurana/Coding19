package com.sapient.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Represents a theatre in the ticketing system.
 * 
 * @author Arun Khurana
 *
 */
@Entity
public class Theatre {
    /**
     * The unique identifier for the theatre.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theatre_id;

    /**
     * The name of the theatre.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * The location of the theatre.
     */
    @NotBlank(message = "Town Name is required")
    private String town;

	/**
	 * @return the theatre_id
	 */
	public Long getTheatre_id() {
		return theatre_id;
	}

	/**
	 * @param theatre_id the theatre_id to set
	 */
	public void setTheatre_id(Long theatre_id) {
		this.theatre_id = theatre_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public String toString() {
		return "Theatre [theatre_id=" + theatre_id + ", name=" + name + ", town=" + town + "]";
	}

}
