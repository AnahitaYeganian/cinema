package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.MovieReservation;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional // Operazione di aggiornamento dei dati nel DB
	public void saveUser(User user) {
		this.userRepository.save(user);
	}
	
	public User getUser(Long id) {
		return this.userRepository.findById(id).get();
	}
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		
		for(User u: this.userRepository.findAll())
			users.add(u);
		
		return users;
	}
	
	public CredentialsService getCredentialsService() {
		return this.credentialsService;
	}
	
	public User getUserByUsername(String username) {
		return this.credentialsService.findByUsername(username).get().getUser();
	}
	
	public List<MovieReservation> findAllReservationPerUser(Long id) {
		List<MovieReservation> reservations = new ArrayList<MovieReservation>();
		for(MovieReservation r : this.userRepository.findAllReservationPerUser(id))
			reservations.add(r);
		return reservations;
	}

}
