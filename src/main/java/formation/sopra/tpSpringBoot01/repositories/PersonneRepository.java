package formation.sopra.tpSpringBoot01.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import formation.sopra.tpSpringBoot01.model.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
	//g�n�re automatiquement l'entr�e et la sortie.
	List<Personne> findByPrenom(String prenom);
	
	
	//utilisation like
	List<Personne> findByPrenomLike(String prenom);
	
	//Au dessus, en dessous, entre
	List<Personne> findByDtNaissGreaterThan(Date dtNaiss);
	List<Personne> findByDtNaissLessThan(Date dtNaiss);
	List<Personne> findByDtNaissBetween(Date min, Date max);
	
	//annotation query pour creer ici nos requetes. (en jpql)
//	@Query("select p from Personne p where p.prenom like: prenom and p.nom like:nom")
//	List<Personne> findByNomAndPrenom(String prenom, String nom); //ici on est sens� mettre le nom de la m�thode qui appelle cette requete

	
	
	
}
