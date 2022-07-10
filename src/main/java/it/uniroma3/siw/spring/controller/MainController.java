package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping({"/", "/index"})
	public String showIndexPage() {
		return "index.html";
	}
	
	@GetMapping({"/userHome"})
	public String backToUserHome() {
		return "home.html";
	}
	
	@GetMapping({"/adminHome"})
	public String backToAdminHome() {
		return "admin/home.html";
	}
	
}