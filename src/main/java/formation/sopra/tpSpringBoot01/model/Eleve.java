package formation.sopra.tpSpringBoot01.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("eleve") // pour strat heritage 1 table
public class Eleve extends Personne {
	private String formation;
	public Eleve() {
	}
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	
}
