package fr.bougly.model;

import javax.persistence.Entity;

@Entity
public class Responsable extends Enseignant {

	private static final long serialVersionUID = 1303624143421117304L;
	
	public Responsable() {
		super();
	}

	public Responsable(String mail, String mdp, String nom, String prenom, String dateDeNaissance) {
		super(mail, mdp, nom, prenom, dateDeNaissance);
	}


}
