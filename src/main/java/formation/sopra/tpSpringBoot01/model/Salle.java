package formation.sopra.tpSpringBoot01.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.tpSpringBoot01.model.jsonview.Common;
import formation.sopra.tpSpringBoot01.model.jsonview.JsonViews;
import formation.sopra.tpSpringBoot01.model.jsonview.SalleWithPersonne;

@Entity
@Table(name = "room")
@NamedQueries({@NamedQuery(name = "Salle.findAll", query ="select s from Salle s"),
	@NamedQuery(name="Salle.findByNameLike", query = "select s from Salle s where s.nom like :nom"),  //permet d'�crire des requetes ici.
	@NamedQuery(name = "Salle.findAllWithPersonnes", query ="select distinct s from Salle s left join fetch s.personnes"), // jointure : join s.personnes avec personnes le set de cette classe. attention : info ferm�es quand em ferm� si on ne mets pas fetch
	@NamedQuery(name = "Salle.findIdKeyWithPersonnes", query ="select s from Salle s left join fetch s.personnes where s.nom = :nom")})
	public class Salle {
	@Id
	@Column(name="name", length = 10)
//	@JsonView(Common.class) //autre possibilité que jsonIgnore : inverse : on le met là où on veut récupérer les attributs
	@JsonView(JsonViews.Common.class) // avec gestion centralisée des marqueurs dans la classe JsonViews
	private String nom;

	
	//Attention : quand on veut faire des connexions entre entit�es , on ne peux utiliser que list et set (collection sans doublon)
	// Plusieurs collections dans une entit� : mettre des sets car une list max.
	@OneToMany(mappedBy = "salle" ) //relation virtuelle : n'existe pas dans bdd (pas de colonne) ; mappedBy : lui dit o� aller chercher l'information pour faire 
	//@JsonIgnore //permet de ne pas faire de boucles infinies quand on demande toutes les salles
	@JsonView(JsonViews.SalleWithPersonne.class) // on le veut quand on demande sallewithpersonne
	private Set<Personne> personnes;
	
	@Version // empecher conflit versions
	private int version;
	
	
	
	public Salle() {
	}

	public Salle(String nom) {
		this.nom = nom;
	}

	public Set<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(Set<Personne> personnes) {
		this.personnes = personnes;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salle other = (Salle) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	
	
}
