package it.uniroma3.siw.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.spring.model.Movie;
import it.uniroma3.siw.spring.service.MovieCatalogService;

@Controller
public class MovieCatalogController {
	
	@Autowired
	private MovieCatalogService movieCatalogService;
	
	@GetMapping("/movieCatalog")
    public String getMoviesInCatalog(Model model) {
    	List<Movie> movies = this.movieCatalogService.findMoviesInCatalog();
		model.addAttribute("movies", movies);
		return "index.html";
    }

}
