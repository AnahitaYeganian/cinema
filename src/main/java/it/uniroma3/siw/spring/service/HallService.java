package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Hall;
import it.uniroma3.siw.spring.repository.HallRepository;

@Service
public class HallService {
	
	@Autowired
	private HallRepository hallRepository;
	
	public List<Hall> findAll() {
		List<Hall> halls = new ArrayList<Hall>();
		for(Hall h : this.hallRepository.findAll())
			halls.add(h);
		return halls;
	}
	
	public Hall getHall(Long id) {
		return this.hallRepository.findById(id).get();
	}

}
