package it.uniroma3.siw.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.spring.model.Hall;
import it.uniroma3.siw.spring.service.HallService;

@Controller
public class HallController {
	
	@Autowired
	private HallService hallService;
	
	@GetMapping("/halls")
	public String getHalls(Model model) {
		List<Hall> halls = this.hallService.findAll();
		model.addAttribute("halls", halls);
		return "index.html";
	}

}
