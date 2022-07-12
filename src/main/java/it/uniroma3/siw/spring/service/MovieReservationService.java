package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

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
	
	@Transactional
	public void saveReservation(MovieReservation reservation) {
		Hall salaPrenotata = reservation.getHall();
		salaPrenotata.setCapacity(salaPrenotata.getCapacity()-1);
		this.movieReservationRepository.save(reservation);
	}
	
	public List<MovieReservation> findAllReservationPerUser(Long id) {
		List<MovieReservation> reservations = new ArrayList<MovieReservation>();
		for(MovieReservation r : this.movieReservationRepository.findAllReservationPerUser(id))
			reservations.add(r);
		return reservations;
	}
	
	@Transactional
	public void deleteReservationById(Long reservationId) {
		this.movieReservationRepository.deleteById(reservationId);
	}

}
