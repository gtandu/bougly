package fr.bougly.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.bougly.model.Administrateur;
import fr.bougly.model.Classe;
import fr.bougly.model.Compte;
import fr.bougly.model.Enseignant;
import fr.bougly.model.Etudiant;
import fr.bougly.model.Filiere;
import fr.bougly.model.Responsable;
import fr.bougly.repository.*;

@Controller
public class LoginController {

	
	public static final String URL_LOGIN_PAGE = "/login.html";

	@RequestMapping(value = URL_LOGIN_PAGE, method = RequestMethod.GET)
	public ModelAndView showLoginPage() throws Exception {

		initUser();
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		
		return model;
	}

	private void initUser() throws Exception {

		// COMPTE ADMIN
		//Compte admin = new Administrateur("admin@hotmail.fr","adm","MAPELLA","Corentin","31/05/1994");
		//	admin = administrateurService.saveUser(admin);

		// COMPTE ETUDIANT
		//Compte etudiant = new Etudiant("etudiant@hotmail.fr","etu","TANDU","Glodie","21/05/1994","20156351");
		//etudiant = etudiantService.saveUser(etudiant);

		//COMPTE ENSEIGNANT
		//Enseignant enseignant = new Enseignant("enseignant@hotmail.fr","ens","FINN","José","31/05/1994");
		//enseignant = enseignantService.saveUser(enseignant);
		
		//COMPTE RESPONSABLE
		//Compte responsable = new Responsable("responsable@hotmail.fr","res","ONYME","Anne","21/05/1994");
		//responsable = responsableService.saveUser(responsable);
		
		//CLASSE
		Classe m1miaa = new Classe("MIAGE", "M1", "Apprentissage");
		ClasseRepository.saveClasse(m1miaa);
		
		//FILIERE
		Filiere miage = new Filiere("Méthodes informatiques appliquées à la gestion des entreprises", 10);
		FiliereRepository.saveFiliere(miage);
	}
}
