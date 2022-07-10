package it.uniroma3.siw.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.repository.MovieReservationRepository;

@Service
public class MovieReservationService {
	
	@Autowired
	private MovieReservationRepository movieReservationRepository;

}
