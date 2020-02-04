package formation.sopra.tpSpringBoot01.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.tpSpringBoot01.model.Salle;

//crude de base => remplace dao
public interface SalleRepository extends JpaRepository<Salle, String>{

	
	//Repository peut utiliser une requete nomm�e par son nom dans les classes si les noms sont les bons
	List<Salle> findAllWithPersonnes();
	
}
