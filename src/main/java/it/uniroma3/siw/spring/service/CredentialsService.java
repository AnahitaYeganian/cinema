package it.uniroma3.siw.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.CredentialsRepository;

@Service
public class CredentialsService {

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@Autowired
	protected CredentialsRepository credentialsRepository;

	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}

	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}

	@Transactional
	public Credentials saveCredentials(Credentials credentials) {
		credentials.setRole(Credentials.CUSTOMER_ROLE);
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		return this.credentialsRepository.save(credentials);
	}
	
	public String getRoleAuthenticated() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = this.getCredentials(userDetails.getUsername());
		return credentials.getRole();
	}
	
	public User getUserAuthenticated() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = this.getCredentials(userDetails.getUsername());
		return credentials.getUser();
	}
	
	public Optional<Credentials> findByUsername(String username) {
		return this.credentialsRepository.findByUsername(username);

	}
	
	public Optional<Credentials> findByUser(User user) {
		return this.credentialsRepository.findByUser(user);
	}
	
	public Credentials findCredentialsByUser_Id(Long id) {
		Credentials credentials = this.credentialsRepository.findCredentialsByUser_Id(id);
		return credentials;
	}
	
}
