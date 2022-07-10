package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Movie;
import it.uniroma3.siw.spring.model.MovieCatalog;

public interface MovieCatalogRepository extends CrudRepository<MovieCatalog, Long> {
	
	@Query("SELECT m FROM MovieCatalog mc JOIN mc.movies m WHERE mc.name='Visible'")
	public List<Movie> findMoviesInCatalog();
	
	public Optional<MovieCatalog> findByName(String name);
	
}