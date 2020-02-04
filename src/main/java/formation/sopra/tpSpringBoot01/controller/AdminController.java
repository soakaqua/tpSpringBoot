package formation.sopra.tpSpringBoot01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/admin")
public class AdminController {

	@GetMapping("/home")
	public String welcomeAdmin() {
		return "admin/home";
	}
	
}
