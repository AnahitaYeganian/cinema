package it.uniroma3.siw.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.CredentialsValidator;
import it.uniroma3.siw.spring.controller.validator.UserValidator;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.CredentialsService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@GetMapping("/register")
	public String showRegistrazioneForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "registrationForm.html";
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		return "loginForm.html";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "index.html";
	}
	
	@GetMapping("/default")
    public String defaultAfterLogin(Model model, HttpServletRequest request) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	
//    	session.setAttribute("userId", credentials.getUser().getId());
//    	session.setAttribute("credentialsId", credentials.getId());
    	
    	model.addAttribute("userId", credentials.getUser().getId());
    	model.addAttribute("credentialsId", credentials.getId());
    	
    	HttpSession session = request.getSession(true);
    	
    	session.setAttribute("currentUser", credentials.getUser());
    	session.setAttribute("currentCredentials", credentials);
    	
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/home.html";
        }
        return "home.html";
    }
	
    @PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult userBindingResult,
                 @Valid @ModelAttribute("credentials") Credentials credentials, BindingResult credentialsBindingResult, Model model) {
    	
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);
        
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("registrationCompleted", new String("Registration completed"));
            return "loginForm.html";
        }
        
        return "registrationForm.html";
    }
}