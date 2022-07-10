package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.spring.service.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;

}
