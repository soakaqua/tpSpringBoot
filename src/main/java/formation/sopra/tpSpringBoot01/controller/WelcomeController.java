package formation.sopra.tpSpringBoot01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping(value = {"", "/"})
	public String welcome() {
		return "index";
	}
	
}
