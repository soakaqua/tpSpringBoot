package formation.sopra.tpSpringBoot01.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
@DiscriminatorValue("formateur") // pour single table heritage !
public class Formateur extends Personne {

	private Integer experience;
	private Boolean referent; 
	@OneToMany(mappedBy = "key.formateur") //erreur bidon ici(quand on fouille dans un objet) // MappedBy : lui indiquer ou trouver le formateur li� par la relation //key : dans enseignement : obj Enseignement PK ; formateur : attribut de key(formateur, competence)
	private Set<Enseignement> enseignements;
	
	
	
// remplac� par table + 2 manytoOne
//	@ManyToMany
//	@JoinTable(name="trainer_skill",
//	joinColumns = @JoinColumn(name="trainer_id", foreignKey = @ForeignKey(name="trainer_skill_trainer_id_fk")), // trainer_id prend l'id de personne
//	inverseJoinColumns = @JoinColumn(name="skill_id"),foreignKey = @ForeignKey(name="trainer_skill_skill_id_fk")) 
//	private Set<Competence> competences;

	public Formateur() {
	}



	public Formateur(@NotEmpty String prenom, String nom) {
		super(prenom, nom);
	}



	public Set<Enseignement> getEnseignements() {
		return enseignements;
	}

	public void setEnseignements(Set<Enseignement> enseignements) {
		this.enseignements = enseignements;
	}


	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

//	public Set<Competence> getCompetences() {
//		return competences;
//	}
//
//	public void setCompetences(Set<Competence> competences) {
//		this.competences = competences;
//	}

	public Boolean getReferent() {
		return referent;
	}

	public void setReferent(Boolean referent) {
		this.referent = referent;
	}



}
