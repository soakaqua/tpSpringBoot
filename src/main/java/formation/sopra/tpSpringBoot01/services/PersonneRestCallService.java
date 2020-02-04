package formation.sopra.tpSpringBoot01.services;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import formation.sopra.tpSpringBoot01.model.Eleve;
import formation.sopra.tpSpringBoot01.model.Formateur;
import formation.sopra.tpSpringBoot01.model.Personne;

@Service
public class PersonneRestCallService {
	// final : pas modifiable (constante)
	private static final String REST_SERVICE_URL="http://localhost:8080/demo/rest/personne";

	//car authentification globale => 
	//Fabrique des restTemplate préconfigurés=> ici préconfigure pour login et mdp (évite de gérer authentification à chaque fois)
	private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().basicAuthentication("az", "toto");


	//Exemple GET
	public Personne findById(Integer id) {
		//restTemplate : appeler un webservice => appelere les méthodes dans le webservice : attention, on doit passer par le type de mapping utilisé par la méthode.
		//utilisation restTemplateBuild pour ne pas gérer login et mdp
		RestTemplate restTemplate= restTemplateBuilder.build();
		//appelle méthode => on passer par getForObject pour mapping get ; on utilise l'url du controller pour l'appeler; on demande une personne en retour
		return restTemplate.getForObject(REST_SERVICE_URL+"/"+id+"/salle",Personne.class);
	}

	//Exemple POST : créer formateur
	public URI createFormateur(Formateur f) {
		RestTemplate restTemplate= restTemplateBuilder.build();
		return restTemplate.postForLocation(REST_SERVICE_URL+"/f", f, Formateur.class);

	}

	//Exemple POST : créer eleve
	public URI createEleve(Eleve e) {
		RestTemplate restTemplate= restTemplateBuilder.build();
		return restTemplate.postForLocation(REST_SERVICE_URL+"/e", e, Eleve.class);
	}


	//delete avec personne
	public void delete(Personne p)
	{
		delete(p.getId());
	}

	// @deleteMapping utilisé
	//delete avec id
	public void delete(Integer id)
	{
		RestTemplate restTemplate= restTemplateBuilder.build();
		restTemplate.delete(REST_SERVICE_URL+"/"+id);
	}

	//update
	public void updateFormateur(Formateur f) {
		RestTemplate restTemplate= restTemplateBuilder.build();
		restTemplate.put(REST_SERVICE_URL+"/"+f.getId()+"/f", f);
	}

	public void updateEleve(Eleve e) {
		RestTemplate restTemplate= restTemplateBuilder.build();
		restTemplate.put(REST_SERVICE_URL+"/"+e.getId()+"/e", e);
	}

	//Findall
	public void findAll() {
		RestTemplate restTemplate= restTemplateBuilder.build();
		//attention probleme: on récupère la liste avec ses objets en json !
		// => méthode 1 : récupérer chaque objet json
//		List<LinkedHashMap<String, Object>>	personnes = restTemplate.getForObject(REST_SERVICE_URL, List.class); 	
//		for(LinkedHashMap<String, Object> personneEnJson : personnes) {
//			System.out.println(personneEnJson);		
//		}
		//meilleure solution ! => on passe par un tableau pour transformer les personne json en personne java
		Personne[] personnes = restTemplate.getForObject(REST_SERVICE_URL, Personne[].class);
		for(Personne p: personnes) {
			System.out.println(p);
		}
	}



}
