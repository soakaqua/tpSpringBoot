package formation.sopra.tpSpringBoot01.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import formation.sopra.tpSpringBoot01.model.Login;
import formation.sopra.tpSpringBoot01.model.Role;
import formation.sopra.tpSpringBoot01.model.UserRole;
import formation.sopra.tpSpringBoot01.repositories.LoginRepository;
import formation.sopra.tpSpringBoot01.repositories.UserRoleRepository;

@Controller
public class InscriptionController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@GetMapping("/inscription")
	public String inscription(Model model) {
		model.addAttribute("login", new Login());
		return "inscription";
	}
	
	@PostMapping("/saveLogin")
	public String saveLogin(@ModelAttribute("login") Login login, Model model) {
		
		//gérer encryptage
		login.setPassword(passwordEncoder.encode(login.getPassword()));
		login.setEnable(true);
		loginRepository.save(login);

		UserRole userRole = new UserRole();
		userRole.setLogin(login);
		userRole.setRole(Role.ROLE_USER);
		userRoleRepository.save(userRole);
		
		return "redirect:/login";
	}
	
}
