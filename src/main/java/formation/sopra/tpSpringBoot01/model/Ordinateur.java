package formation.sopra.tpSpringBoot01.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ordinateur")
public class Ordinateur extends Materiel {

	private int ram;

	public Ordinateur() {
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}
	
	
	
}
