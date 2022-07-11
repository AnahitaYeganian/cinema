package it.uniroma3.siw.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Hall;
import it.uniroma3.siw.spring.model.MovieReservation;
import it.uniroma3.siw.spring.repository.MovieReservationRepository;

@Service
public class MovieReservationService {
	
	@Autowired
	private MovieReservationRepository movieReservationRepository;
	
	@Autowired
	private HallService hallService;
	
	@Transactional
	public void saveReservation(MovieReservation reservation) {
		Hall salaPrenotata = reservation.getHall();
		salaPrenotata.setCapacity(salaPrenotata.getCapacity()-1);
		this.movieReservationRepository.save(reservation);
	}

}
