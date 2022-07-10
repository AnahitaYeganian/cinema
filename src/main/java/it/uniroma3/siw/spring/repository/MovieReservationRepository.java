package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.MovieReservation;

public interface MovieReservationRepository extends CrudRepository<MovieReservation, Long> {

}
