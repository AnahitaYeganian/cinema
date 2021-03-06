package it.uniroma3.siw.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Director;
import it.uniroma3.siw.spring.repository.DirectorRepository;

@Service
public class DirectorService {
	
	@Autowired
	private DirectorRepository directorRepository;
	
	@Transactional
	public void saveDirector(Director director) {
		this.directorRepository.save(director);
	}

}