package formation.sopra.tpSpringBoot01.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "trainer_skill")
public class Enseignement {


	@EmbeddedId //classe embarqu�e utilis�e comme id // Message d'erreur : bidon => modif dans les propri�t�s du projet : JPA --> info
	private EnseignementPK key;
	@Column(name="level", length = 50)
	private String niveau;
	
	@Version // on doit le mettre car on a un attribut niveau. Si cette table n'�tait que de l'association, pas besoin.
	private int version;
	
	
	public Enseignement() {
		
	}

	public Enseignement(EnseignementPK key, String niveau) {
		this.key = key;
		this.niveau = niveau;
	}

	public EnseignementPK getKey() {
		return key;
	}

	public void setKey(EnseignementPK key) {
		this.key = key;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		Enseignement other = (Enseignement) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
	
	
	
}
