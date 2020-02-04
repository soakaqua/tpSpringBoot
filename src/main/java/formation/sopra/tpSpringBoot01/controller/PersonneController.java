package formation.sopra.tpSpringBoot01.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import formation.sopra.tpSpringBoot01.model.Civilite;
import formation.sopra.tpSpringBoot01.model.Eleve;
import formation.sopra.tpSpringBoot01.model.Formateur;
import formation.sopra.tpSpringBoot01.model.Personne;
import formation.sopra.tpSpringBoot01.repositories.PersonneRepository;
import formation.sopra.tpSpringBoot01.repositories.SalleRepository;

@Controller
@RequestMapping("/auth/personne") //permet de mettre l'url "/personne" automatiquement avant list et edit
public class PersonneController {
	
	@Autowired
	PersonneRepository personneRepository; // permet d'appeler les m�thodes du repository (de l'autre projet)
	
	@Autowired
	SalleRepository salleRepository;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("personne", personneRepository.findAll());
		
		return "personne/list";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam(name="id") Integer id,Model model) {
		//On repasse par un findById pour le r�cup�rer au cas o� l'objet a �t� modifi� depuis l'affichage
		Optional<Personne> opt = personneRepository.findById(id);
		Personne p = null; 
		if(opt.isPresent()) {
			p = opt.get();
		}
		model.addAttribute("personne", p);

		return goEdit(p, model);
	}
	
	@GetMapping("/addFormateur")
	public String addFormateur(Model model) {
		return goEdit(new Formateur(), model);	
	}
	
	@GetMapping("/addEleve")
	public String addEleve(Model model) {
		return goEdit(new Eleve(), model);
	}
	
	private String goEdit(Personne p, Model model) {
		model.addAttribute("personne", p);
		model.addAttribute("civilites", Civilite.values());
		model.addAttribute("salles", salleRepository.findAll());
		return "personne/edit";
	}
	
	
	@PostMapping("/saveEleve") //on utilise un model attribute => pas de classe abstraite => en faire une pour eleve et une autre pour formateur
	public String saveEleve(@ModelAttribute("personne") @Valid  Eleve personne , BindingResult br , Model model) {
		return save(personne,br, model);
	}
	
	@PostMapping("/saveFormateur") //on utilise un model attribute => pas de classe abstraite => en faire une pour eleve et une autre pour formateur
	//@valid : quand spring creer l'objet : v�rifie que les contraintes qu'on a mis sur la classe personne sont respect�es.
	//Binding result : d�tails si l'objet g�n�r� par le model est  non valide
	public String saveFormateur(@ModelAttribute("personne") @Valid Formateur personne, BindingResult br , Model model) { 
		return save(personne,br, model);
	}
	
	
	private String save(Personne personne, BindingResult br, Model model) {
		//cas o� on s�lectionne pas de salle dans l'edit.
		if(personne.getSalle()!=null && personne.getSalle().getNom().isEmpty()) {
			personne.setSalle(null);
		}
		//utilisation du binding result
		//attention : on doit aussi ajouter une validator dans le dispatcher
		if(br.hasErrors()) {
			return "personne/edit";
		}
		
		personneRepository.save(personne);
		return "redirect:/auth/personne/list"; //redirect : ce qu'on renvoie n'est plus trait� commme une vue mais comme une demande d'une autre page => relancer /list
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name ="id") Integer id) {
		personneRepository.deleteById(id);
		return "redirect:/auth/personne/list";
	}
	
}
