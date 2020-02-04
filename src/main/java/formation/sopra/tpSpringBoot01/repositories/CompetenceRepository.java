package formation.sopra.tpSpringBoot01.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.tpSpringBoot01.model.Competence;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
	
	//m�thode qui renvoie une page.
	Page<Competence>findAllByNomContaining(String nom, Pageable pageable);

	
	//tri :
	
	
}
