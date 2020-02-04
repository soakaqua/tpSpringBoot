package formation.sopra.tpSpringBoot01.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.tpSpringBoot01.model.Eleve;
import formation.sopra.tpSpringBoot01.model.Formateur;
import formation.sopra.tpSpringBoot01.model.Personne;
import formation.sopra.tpSpringBoot01.model.Salle;
import formation.sopra.tpSpringBoot01.model.jsonview.JsonViews;
import formation.sopra.tpSpringBoot01.repositories.PersonneRepository;
import formation.sopra.tpSpringBoot01.services.PersonneService;

@RestController
@RequestMapping("/rest/personne")
public class PersonneRestController {

	@Autowired
	private PersonneRepository personneRepository;

	@Autowired
	private PersonneService personneService;
	
	//findALL
	@GetMapping({"","/"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Personne>> list(){
		return new ResponseEntity<List<Personne>>(personneRepository.findAll(),HttpStatus.OK);
	}

	//findAll with salle
	@GetMapping("/salle")
	@JsonView(JsonViews.PersonneWithSalle.class)
	public ResponseEntity<List<Personne>> listWithSalle(){
		return new ResponseEntity<List<Personne>>(personneRepository.findAll(),HttpStatus.OK);
	}

	//FindById
	@GetMapping("/{id}/salle") //{id} : paramet de récupérer ce param, fonctionne avec le @pathvariable
	@JsonView(JsonViews.PersonneWithSalle.class)
	public ResponseEntity<Personne> personneByIdWithSalle(@PathVariable("id") Integer id){
		Optional<Personne> opt= personneRepository.findById(id);
		if(opt.isPresent()) {
			return new ResponseEntity<Personne>(opt.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//Add Formateur
	@PostMapping("/f")
	public ResponseEntity<Void> addFormateur(@RequestBody Formateur formateur, UriComponentsBuilder uCB){
		personneRepository.save(formateur);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(uCB.path("/rest/personne/{id}").buildAndExpand(formateur.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	//Add Eleve
	@PostMapping("/e")
	public ResponseEntity<Void> addEleve(@RequestBody Eleve eleve, UriComponentsBuilder uCB){
		personneRepository.save(eleve);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(uCB.path("/rest/personne/{id}").buildAndExpand(eleve.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		Optional<Personne> opt = personneRepository.findById(id);
		if(opt.isPresent()) {
			personneRepository.delete(opt.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}

	
	
	//Update formateur
	//On pouvait aussi faire une 3e méthode pour sauvegarder une personne en l'appelant dans les deux suivantes avec comme param une personne et l'id
	@PutMapping("/{id}/f")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id,@RequestBody Formateur formateur ){
		formateur.setId(id);
		Boolean transOk = personneService.save((Personne) formateur);
		if(transOk==true) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	//Update eleve
	@PutMapping("/{id}/e")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id,@RequestBody Eleve eleve ){
		eleve.setId(id);
		Boolean transOk = personneService.save((Personne) eleve);
		if(transOk==true) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}


}
