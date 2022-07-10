package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}