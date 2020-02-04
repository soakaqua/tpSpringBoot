package formation.sopra.tpSpringBoot01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import formation.sopra.tpSpringBoot01.model.Salle;
import formation.sopra.tpSpringBoot01.repositories.SalleRepository;

@Controller
@RequestMapping("/auth/salle") //permet de mettre l'url "/personne" automatiquement avant list et edit
public class SalleController {

	@Autowired
	SalleRepository salleRepository;
	

	
	@GetMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("salle/list", "salles", salleRepository.findAll()); //on peut renvoyer un modelAndView qui permet de g�rer les models et la vue en return
	}

	
	//remplac� par une m�thode avec ModelAndViewa
//	@GetMapping("/addSalle")
//	public String addSalle(Model model) {
//		model.addAttribute("salle", new Salle() );
//		return "salle/edit";
//	}
	
	@GetMapping("/addSalle")
	public ModelAndView addSalle() {
		return new ModelAndView("salle/edit", "salle", new Salle());
	}
	

	@PostMapping("/saveSalle") //on utilise un model attribute => pas de classe abstraite => en faire une pour eleve et une autre pour formateur
	public String saveSalle(@ModelAttribute Salle salle , Model model) {
		
		salleRepository.save(salle);
		return "redirect:/auth/salle/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name ="nom") String nom) {
		salleRepository.deleteById(nom);
		return "redirect:/auth/salle/list";
	}
	
}
