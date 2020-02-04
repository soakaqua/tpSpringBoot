package formation.sopra.tpSpringBoot01.model;

public class Chaise {
	private String taille;
	private Integer confort;
	private Coussin coussin;
	
	public Chaise() {
		
	}
	
	
	
	public Chaise(String taille, Integer confort, Coussin coussin) {
		super();
		this.taille = taille;
		this.confort = confort;
		this.coussin = coussin;
	}



	public Coussin getCoussin() {
		return coussin;
	}



	public void setCoussin(Coussin coussin) {
		this.coussin = coussin;
	}



	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}
	public Integer getConfort() {
		return confort;
	}
	public void setConfort(Integer confort) {
		this.confort = confort;
	}
	
}
