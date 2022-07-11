package it.uniroma3.siw.spring.controller;

import java.util.List;

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

import it.uniroma3.siw.spring.controller.validator.MovieReservationValidator;
import it.uniroma3.siw.spring.model.Hall;
import it.uniroma3.siw.spring.model.Movie;
import it.uniroma3.siw.spring.model.MovieReservation;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.MovieCatalogService;
import it.uniroma3.siw.spring.service.MovieReservationService;
import it.uniroma3.siw.spring.service.MovieService;

@Controller
public class MovieReservationController {
	
	@Autowired
	private MovieReservationService movieReservationService;
	
	@Autowired
	private MovieReservationValidator reservationValidator;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieCatalogService movieCatalogService;
	
	@GetMapping("/home/reservation/{id}")
	public String doAReservation(@PathVariable("id") Long id, Model model, HttpSession session) {
		MovieReservation reservation = new MovieReservation();
		Movie selectedMovie = this.movieService.getMovie(id);
		Hall selectedHall = selectedMovie.getHall();
		User currentUser = (User)session.getAttribute("currentUser");
		
		reservation.setMovie(selectedMovie);
		reservation.setHall(selectedHall);
		reservation.setUser(currentUser);
		
		model.addAttribute("reservation", reservation);
		
		return "reservationDetailsForm.html";
	}
	
	@PostMapping("/home/newReservation")
	public String doAReservation(@Valid @ModelAttribute("reservation") MovieReservation reservation, BindingResult reservationBindingResult, Model model, HttpSession session) {
		this.reservationValidator.validate(reservation, reservationBindingResult);

        if(!reservationBindingResult.hasErrors()) {
        	this.movieReservationService.saveReservation(reservation);
            model.addAttribute("movies", this.movieCatalogService.findMoviesInCatalog());
            return "home.html";
        }
        
        return "reservationDetailsForm.html";
	}
	
	@GetMapping("/home/reservation2")
	public String getAllReservationPerUser(Model model, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		List<MovieReservation> reservations = this.movieReservationService.findAllReservationPerUser((Long)currentUser.getId());
		
		model.addAttribute("reservations", reservations);
		return "myReservations.html";
	}
	
	@GetMapping("/home/reservation/cancelReservation/{id}")
	public String deleteReservationById(@PathVariable("id") Long reservationId, Model model, HttpSession session) {
		this.movieReservationService.deleteReservationById(reservationId);
		
		User currentUser = (User)session.getAttribute("currentUser");
		List<MovieReservation> reservations = this.movieReservationService.findAllReservationPerUser((Long)currentUser.getId());
		model.addAttribute("reservations", reservations);
		
		return "myReservations.html";
	}

}