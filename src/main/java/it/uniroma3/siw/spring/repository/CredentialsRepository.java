package it.uniroma3.siw.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.User;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	
	public Optional<Credentials> findByUsername(String username);
	
	public Optional<Credentials> findByUser(User user);
	
	@Query("SELECT c FROM Credentials c JOIN c.user u WHERE c.user.id=:id")
	public Credentials findCredentialsByUser_Id(Long id);
	
}