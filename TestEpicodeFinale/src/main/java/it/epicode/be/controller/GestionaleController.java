package it.epicode.be.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GestionaleController {

	//TODO  - scegliere se escludere da security
	@GetMapping("/")
	public String home() {
		return "home";
	}

}
