package formation.sopra.tpSpringBoot01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import formation.sopra.tpSpringBoot01.model.Civilite;
import formation.sopra.tpSpringBoot01.model.Formateur;
import formation.sopra.tpSpringBoot01.model.Salle;
import formation.sopra.tpSpringBoot01.repositories.LoginRepository;
//implements et service : déléguer gestion console à spring. => point d'entrée dans programme ici.
@Service
public class ConsoleService implements CommandLineRunner{

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	PersonneRestCallService personneRestCallService;

	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("hello world par springBoot");
//		System.out.println(passwordEncoder.encode("toto"));
//		System.out.println(personneRestCallService.findById(101));
		
////		//test add 
////		Formateur f = new Formateur("aaa","mmm");
////		System.out.println(personneRestCallService.createFormateur(f)); // renvoie uri du formateur créé
//
		Formateur f  =  (Formateur) personneRestCallService.findById(105) ;
//
//		f.setCivilite(Civilite.Mme);
//		f.setExperience(20);
//		personneRestCallService.updateFormateur(f);
//		
//		
//		personneRestCallService.delete(105);
		

		
	}

	
	
}
