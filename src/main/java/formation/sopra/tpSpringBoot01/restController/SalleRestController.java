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

import formation.sopra.tpSpringBoot01.model.Salle;
import formation.sopra.tpSpringBoot01.model.jsonview.JsonViews;
import formation.sopra.tpSpringBoot01.repositories.SalleRepository;

//ne plus renvoyer de vue mais du json (compréhensible par tous les langages.)
//controller spring rest (webservice) => renvoie du json

@RestController
@RequestMapping("/rest/salle")
public class SalleRestController {

	@Autowired
	private SalleRepository salleRepository;
	
	//dans ce mapping : on peut y acceder avec url avec ou sans /
	@GetMapping({"", "/"})
	//	@JsonView(Common.class) //json view : eviter boucle infinie entre salle et personne. on doit creer une classe vide qu'on défini comme marqueur dans la classe salle
	@JsonView(JsonViews.Common.class) //version classes vides centralisées
	public ResponseEntity<List<Salle>> list(){

		return new ResponseEntity<List<Salle>>(salleRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/personne")
	@JsonView(JsonViews.SalleWithPersonne.class)
	public ResponseEntity<List<Salle>> listWithPersonne(){
		return new ResponseEntity<List<Salle>>(salleRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}") //{id} : paramet de récupérer ce param, fonctionne avec le @pathvariable
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Salle> salleByKey(@PathVariable("id") String nom){
		Optional<Salle> opt = salleRepository.findById(nom);
		if (opt.isPresent()) {
			return new ResponseEntity<Salle>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	//FindById
	@GetMapping("/{id}/personne") //{id} : paramet de récupérer ce param, fonctionne avec le @pathvariable
	@JsonView(JsonViews.SalleWithPersonne.class)
	public ResponseEntity<Salle> salleByKeyWithPersonne(@PathVariable("id") String nom){
		Optional<Salle> opt = salleRepository.findById(nom);
		if (opt.isPresent()) {
			return new ResponseEntity<Salle>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	//Save
	@PostMapping({"","/"})
	public ResponseEntity<Void> addSalle(@RequestBody Salle salle, UriComponentsBuilder uCB) {
		Optional<Salle> opt=salleRepository.findById(salle.getNom());
		if(opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		salleRepository.save(salle);
		HttpHeaders headers=new HttpHeaders();

		//méthode avec uCB
		headers.setLocation(uCB.path("/rest/salle/{id}").buildAndExpand(salle.getNom()).toUri());

		// Méthode sans uCB : pas ouf en cas de changement d'adresse et en cas d'espaces dans param (nom)
		//			try {
		//				headers.setLocation(new URI("http://localhost:8080/demo/rest/salle/"+salle.getNom()));
		//			} catch (URISyntaxException e) {
		//				e.printStackTrace();
		//			}
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	//Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id){
		Optional<Salle> opt = salleRepository.findById(id);
		if(opt.isPresent()) {
			salleRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		}

//	//Update : non finie
//	@PutMapping("/{id}")
//	public ResponseEntity<Salle> update(@PathVariable("id") String nom, @RequestBody Salle salle){
//		Optional<Salle> opt = salleRepository.findById(nom);
//		if(opt.isPresent()) {
//			Salle salleEnBase=opt.get();
//			//A AJOUTER : meme chose que dans SalleService
//		}
//	}

		//	@GetMapping("/rest/hello")
		//	public String hello() {
		//		return "hello world";
		//	}
		//
		//	//get pour envoyer des données en json
		//	@GetMapping("/rest/chaise")
		//	public ResponseEntity<Chaise> getChaise() { // renvoie responseEntity
		//
		//		return new ResponseEntity<Chaise>(new Chaise("XXL", 20, new Coussin("rose")), HttpStatus.OK) ;
		//	}
		//
		//	@GetMapping("/rest/chaise")
		//public Chaise getChaise() { //renvoie chaise (equivalent responseEntity)
		//	return  new Chaise("XXL", 20, new Coussin("rose"));
		//}

		//	//Méthode pour récupérer un objet json => Post pour récupérer des données json
		//	@PostMapping("/rest/chaise")
		//	//requestBody : instancie objet et balaye param de la requete pour positionner tous les attributs de l'objet.
		//	public ResponseEntity<Void> recupChaise(@RequestBody Chaise chaise){
		//		System.out.println(chaise);
		//		System.out.println(chaise.getConfort());
		//		return new ResponseEntity<Void>(HttpStatus.OK);
		//		
		//	}


	}
