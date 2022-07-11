package it.uniroma3.siw.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Hall;

public interface HallRepository extends CrudRepository<Hall, Long> {
	
	public Optional<Hall> findByName(String name);

}