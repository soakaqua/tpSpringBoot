package formation.sopra.tpSpringBoot01.model;

import java.util.Date;

//pgadmin

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.tpSpringBoot01.model.jsonview.Common;
import formation.sopra.tpSpringBoot01.model.jsonview.JsonViews;
import formation.sopra.tpSpringBoot01.model.jsonview.SalleWithPersonne;


@Entity //dire � Hibernate que c'est une table.
@Table(name="person") //d�fini le nom de la table //si table en com : pour table per class heritage
//Pour appler webservices en java => pas de classe abstraite => on doit savoir qui est eleve/formateur
@JsonTypeInfo(
		use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type"
		)
@JsonSubTypes({
	@Type(value = Formateur.class, name="F"),
	@Type(value = Eleve.class, name="E")
})
@SequenceGenerator(name = "seqPersonne", sequenceName = "seq_person",initialValue = 100, allocationSize = 1) //n�cessaire quand on cr�er la s�quence dans le code java. ; name : nom de la s�quence dans java ; sequenceName: nom de la sequence dans la base
//@Inheritance(strategy = InheritanceType.JOINED) //Permet l'h�ritage sur cette classe (m�re) | Joined : une table par classe
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //2 tables => attention enlever le @table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 1 table ! ajouter discriminatorColumn et discriminatorValue dans classes filles
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length =120) //pour single_table ; attention : on ne g�re pas �a en java, fait tout seul par hibernate => IL REMONTE UN OBJET DE LA CLASSE ELEVE OU FORMATEUR avec select => utiliser instanceOf(objet) pour connaitre le type
public abstract class Personne { //abstract pour strategy d'heritage
	
	//wrapper : classe qui encapsule les types primitifs (ex : Integer, Sring)
	
	@Transient //cet attribut n'apparait pas dans la base de donn�e !
	private String toto;
	


	@Id //id pour entity 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPersonne")
	@JsonView(JsonViews.Common.class)
	private Integer id;
	@NotEmpty
	@Column(name = "first_name", length =150, nullable = false) //nullable : true par d�faut 
	@JsonView(JsonViews.Common.class)
	private String prenom;
	@NotEmpty(message="Le nom est obligatoire !")
	@Column(name="last_name",length = 150, nullable = false)
	@JsonView(JsonViews.Common.class)
	private String nom;
	@Enumerated(EnumType.STRING) //Gerer une �num�ration dans la base de donn�e. Pour EnumType : on devra stocker "M" alors que pour ordinal son indice (0) 
	@Column(name = "title", length= 4)
	@JsonView(JsonViews.Common.class)
	private Civilite civilite;
	
	@JsonView(JsonViews.Common.class)
	@Temporal(TemporalType.DATE)
    @Column(name="dtNaiss")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dtNaiss;
	
	@Embedded //pour embarquer un objet complexe ; attention : on doit mettre @Embeddable dans la classe utilis�e.
	@AttributeOverrides({ // ouvre un tableau o� on va modifier chaque attribut 
		@AttributeOverride(name="numero", column = @Column(name = "person_number", length = 200)), // modifie le nom dans la bdd de l'attribut num�ro avec la colonne person_number
		@AttributeOverride(name="rue", column = @Column(name = "person_street")),
		@AttributeOverride(name="codePostal", column = @Column(name = "person_zip_code")),
		@AttributeOverride(name="ville", column = @Column(name = "person_city"))
	})
	@JsonView(JsonViews.Common.class)
	private Adresse adresse; //En ajoutant des @column dans la classe adresse on peut les stocker dans la base sur des tables. Mais quand on le r�cup�re on re�oit l'objet adresse => utile pour le formatage !!
	
	

	
	//Attention : quand on veut faire des connexions entre entit�es , on ne peux utiliser que list et set (collection sans doublon)
	// Plusieurs collections dans une entit� : mettre des sets car une list max.
	@ManyToOne() //Many personnes to 1 salle
	@JoinColumn(name="room_id", foreignKey = @ForeignKey(name="person_room_id_fk")) //nom de la colonne dans la table personne. Sinon prend l'id de la classe Salle
	@JsonView(JsonViews.PersonneWithSalle.class) // n'est prise que quand on demande depuis personne
	private Salle salle;
	
	//Colonne qui sert � v�rifier qu'on ne fait pas deux op�rations en m�me temps sur un objet personne. Marche comme un compteur : si le compteur est != en entr�e et en sortie, on bloque la requette.
	@Version
	private int version;
	
	
	

	public Personne(@NotEmpty String prenom, String nom) {
		super();
		this.prenom = prenom;
		this.nom = nom;
	}

	public Personne() {
		
	}	

	//@Id //on peut mettre l'Id ici au lieu de l'attribut
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDtNaiss() {
		return dtNaiss;
	}

	public void setDtNaiss(Date dtNaiss) {
		this.dtNaiss = dtNaiss;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}
	

	public void setNom(String nom) {
		this.nom = nom;
	}
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) { //g�n�r� automatiquement : hashers and equals ; doit �tre impl�ment� pour �viter des conflits sur l'id
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
