package it.uniroma3.siw.spring.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.MovieValidator;
import it.uniroma3.siw.spring.model.Movie;
import it.uniroma3.siw.spring.service.HallService;
import it.uniroma3.siw.spring.service.MovieCatalogService;
import it.uniroma3.siw.spring.service.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieValidator movieValidator;
	
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
	
	@GetMapping("/admin/home/updateMovie/{id}")
	public String updateMovie(@PathVariable("id") Long id, Model model, HttpSession session) {
    	Movie movie = this.movieService.getMovie(id);
    	
		model.addAttribute("movie", movie);
		session.setAttribute("currentMovie", movie);
		return "admin/updateMovieDetailsForm.html";
	}
	
	@PostMapping("/admin/home/updateMovie")
    public String updateMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult movieBindingResult, Model model, HttpSession session) {
		
		this.movieValidator.validate(movie, movieBindingResult);
        
		if(!movieBindingResult.hasErrors()) {
			Movie currentMovie = (Movie)session.getAttribute("currentMovie");
			
			currentMovie.setName(movie.getName());
        	currentMovie.setReleaseDate(movie.getReleaseDate());
        	currentMovie.setDirector(movie.getDirector());
        	currentMovie.setHall(this.hallService.findByName(movie.getHall().getName()));
        	
        	this.movieService.saveMovie(currentMovie);
        	
            model.addAttribute("movieUpdateDone", new String("Movie informations have been updated"));
        	model.addAttribute("movie", currentMovie);
        	
            return "admin/movie.html";
        }
        
        return "admin/updateMovieDetailsForm.html";
    }
}