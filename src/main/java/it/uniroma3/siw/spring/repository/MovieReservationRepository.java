package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.MovieReservation;

public interface MovieReservationRepository extends CrudRepository<MovieReservation, Long> {
	
	@Query("SELECT mr FROM MovieReservation mr JOIN mr.user u WHERE u.id=:id ORDER BY mr.startTime")
	public List<MovieReservation> findAllReservationPerUser(Long id);

}
