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

import it.uniroma3.siw.spring.controller.validator.CredentialsValidator;
import it.uniroma3.siw.spring.controller.validator.UserValidator;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.MovieReservation;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@GetMapping("/home/reservation")
	public String getAllReservationPerUser(Model model, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		List<MovieReservation> reservations = this.userService.findAllReservationPerUser((Long)currentUser.getId());
		
		model.addAttribute("reservations", reservations);
		return "myReservations.html";
	}
	
	@GetMapping("/updateUser/{id}")
	public String updateUser(@PathVariable("id") Long userId, Model model, HttpSession session) {
		if(((User)session.getAttribute("currentUser")).getId() == userId) {
			model.addAttribute("user", this.userService.getUser(userId));
			model.addAttribute("credentials", this.userService.getCredentialsService().findCredentialsByUser_Id(userId));
			return "userDetailsForm.html";
		}
		else {
			return "home.html";
		}
	}
	
	@PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult userBindingResult,
                 @Valid @ModelAttribute("credentials") Credentials credentials, BindingResult credentialsBindingResult, Model model, HttpSession session) {
		
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
        	User currentUser = (User)session.getAttribute("currentUser");
        	Credentials currentCredentials = (Credentials)session.getAttribute("currentCredentials");
        	
        	currentUser.setName(user.getName());
        	currentUser.setSurname(user.getSurname());
        	currentCredentials.setUsername(credentials.getUsername());
        	currentCredentials.setPassword(credentials.getPassword());
        	
        	//currentCredentials.setUser(currentUser); non serve
            this.userService.getCredentialsService().saveCredentials(currentCredentials);
            model.addAttribute("updateDone", new String("User informations have been updated"));
        }
        
        return "userDetailsForm.html";
    }

}
