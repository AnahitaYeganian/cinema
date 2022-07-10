package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Movie;
import it.uniroma3.siw.spring.model.MovieCatalog;
import it.uniroma3.siw.spring.repository.MovieCatalogRepository;

@Service
public class MovieCatalogService {
	
	@Autowired
	private MovieCatalogRepository movieCatalogRepository;
	
	public MovieCatalog findByName(String name) {
		return this.movieCatalogRepository.findByName(name).get();
	}
	
	public List<Movie> findMoviesInCatalog() {
		List<Movie> movies = new ArrayList<Movie>();
		for(Movie m: this.movieCatalogRepository.findMoviesInCatalog()) {
			movies.add(m);
		}
		return movies;
	}
	
	
}