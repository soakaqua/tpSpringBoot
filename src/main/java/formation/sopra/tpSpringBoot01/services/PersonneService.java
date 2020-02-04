package formation.sopra.tpSpringBoot01.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.sopra.tpSpringBoot01.model.Personne;
import formation.sopra.tpSpringBoot01.repositories.PersonneRepository;

@Service //type de bean : viens au dessus des Repository == couche m�tier
public class PersonneService {

	@Autowired
	private PersonneRepository personneRepository;

	public boolean save(Personne p) {
		Personne personneEnBase = null;
		if(p.getId()!=null) {
			//Update
			Optional<Personne> opt = personneRepository.findById(p.getId());
			if (opt.isPresent()) { //bien une personne dans l'optionnal
				personneEnBase = opt.get();
				//ci-dessous : a faire pour tous les attributs ! 
				//But : �viter d'�craser des attributs d�j� renseign�s ! (sauf p.get(id))
				personneEnBase.setPrenom((p.getPrenom()!=null)?p.getPrenom():personneEnBase.getPrenom());  //op�rateur ternaire : si prenom dans obj r�cup�r� en argument : on l'ajoute � la personne � mettre � jour | si il n'y est pas : on prend celui de la bdd
				personneEnBase.setNom((p.getNom()!=null)?p.getNom():personneEnBase.getNom());  //op�rateur ternaire : si prenom dans obj r�cup�r� en argument : on l'ajoute � la personne � mettre � jour | si il n'y est pas : on prend celui de la bdd
				personneEnBase.setCivilite((p.getCivilite()!=null)?p.getCivilite():personneEnBase.getCivilite());  //op�rateur ternaire : si prenom dans obj r�cup�r� en argument : on l'ajoute � la personne � mettre � jour | si il n'y est pas : on prend celui de la bdd
				personneEnBase.setDtNaiss((p.getDtNaiss()!=null)?p.getDtNaiss():personneEnBase.getDtNaiss());  //op�rateur ternaire : si prenom dans obj r�cup�r� en argument : on l'ajoute � la personne � mettre � jour | si il n'y est pas : on prend celui de la bdd
				personneEnBase.setId((p.getId()!=null)?p.getId():personneEnBase.getId());  //op�rateur ternaire : si prenom dans obj r�cup�r� en argument : on l'ajoute � la personne � mettre � jour | si il n'y est pas : on prend celui de la bdd
				personneEnBase.setAdresse((p.getAdresse()!=null)?p.getAdresse():personneEnBase.getAdresse());
				personneEnBase.setSalle((p.getSalle()!=null)?p.getSalle():personneEnBase.getSalle());
				personneEnBase.setVersion((p.getVersion()!=0)?p.getVersion():personneEnBase.getVersion());
				
				personneRepository.save(personneEnBase);
				return true;
			}
			return false;
		}
		else {
			//creation
			boolean erreur = false;
			if(p.getPrenom()==null) { //pour champ obligatoire
				erreur = true;
			}
			if(p.getNom()==null) { //pour champ obligatoire
				erreur = true;
			}
			if(!erreur) {
				personneRepository.save(p);
				return true;
			}
			return false; // utile pour savoir si on a eu un probleme

		}

	}
}
