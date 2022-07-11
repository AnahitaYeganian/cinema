package it.uniroma3.siw.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.spring.model.MovieReservation;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/home/reservation")
	public String getAllReservationPerUser(Model model, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		List<MovieReservation> reservations = this.userService.findAllReservationPerUser((Long)currentUser.getId());
		
		model.addAttribute("reservations", reservations);
		return "myReservations.html";
	}

}
