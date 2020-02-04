package formation.sopra.tpSpringBoot01.model;

public enum Civilite {
	M("Monsieur","mister"), Mme("Madame","madam"), Mlle("Mademoiselle","miss");
	
	private String francais;
	private String anglais;
	private Civilite(String francais, String anglais) {
		this.francais = francais;
		this.anglais = anglais;
	}
	public String getFrancais() {
		return francais;
	}

	public String getAnglais() {
		return anglais;
	}


	

}
