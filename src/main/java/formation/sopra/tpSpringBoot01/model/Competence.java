package formation.sopra.tpSpringBoot01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="skill")
@SequenceGenerator(name = "seqCompetence",sequenceName = "seq_skill", initialValue = 50, allocationSize = 1)
public class Competence {
	@Id
	@GeneratedValue(generator = "seqCompetence", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column
	private String nom;
	
	@Version // empecher conflit versions
	private int version;
	
	public Competence() {
		
	}

	public Competence(Integer id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
