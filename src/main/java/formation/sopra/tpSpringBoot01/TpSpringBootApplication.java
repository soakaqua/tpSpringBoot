package formation.sopra.tpSpringBoot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Contient entre autres un component scan : par défaut package racin (formation.sopra.tpSpringBoot01) => creation de classe dans ce package (on peut créer d'autres packages dans ce package)
@SpringBootApplication
public class TpSpringBootApplication {

	public static void main(String[] args) {
		//projet spring boot : ne pas toucher ! (gère le projet spring boot)
		SpringApplication.run(TpSpringBootApplication.class, args);
	}

}
