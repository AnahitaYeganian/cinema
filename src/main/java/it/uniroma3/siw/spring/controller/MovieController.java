package it.uniroma3.siw.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.model.Movie;
import it.uniroma3.siw.spring.service.HallService;
import it.uniroma3.siw.spring.service.MovieCatalogService;
import it.uniroma3.siw.spring.service.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieCatalogService movieCatalogService;
	
	@Autowired
	private HallService hallService;
	
	@GetMapping("/admin/home/movie")
	public String insertNewMovieInMovieCatalog(Model model) {
		Movie newMovie = new Movie();
		model.addAttribute("newMovie", newMovie);
		return "admin/movieDetailsForm.html";
	}
	
	@PostMapping("/admin/home/newMovie")
	public String insertNewMovieInMovieCatalog(@Valid @ModelAttribute("newMovie") Movie newMovie, Model model) {
		/*Director director = new Director();
		director.setName(newMovie.getDirector().getName());
		director.setSurname(newMovie.getDirector().getSurname());
		newMovie.setDirector(director);
		Non serve per via di CascadeType.ALL */
		
		newMovie.setHall(this.hallService.findByName(newMovie.getHall().getName()));
		newMovie.setCatalog(this.movieCatalogService.findByName("Visible"));
		this.movieService.saveMovie(newMovie);
		
		return "admin/home.html";
	}
	
}
