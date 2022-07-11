package it.uniroma3.siw.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Movie;
import it.uniroma3.siw.spring.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Movie getMovie(Long id) {
		return this.movieRepository.findById(id).get();
	}
	
	@Transactional
	public void saveMovie(Movie movie) {
//		Hall salaPrenotata = reservation.getHall();
//		salaPrenotata.setCapacity(salaPrenotata.getCapacity()-1);
		
		this.movieRepository.save(movie);
	}

}
