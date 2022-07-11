package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.MovieReservation;
import it.uniroma3.siw.spring.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("SELECT r FROM User u JOIN u.reservations r WHERE u.id=:id ORDER BY r.startTime")
	public List<MovieReservation> findAllReservationPerUser(Long id);
	
	//@Query("SELECT i FROM Piatto p JOIN p.ingredienti i WHERE p.id=:id")
}
