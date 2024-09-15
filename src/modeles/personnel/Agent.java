package modeles.personnel;

import java.util.Date;

public class Agent extends Personnelle {
	
private String specialite;


public Agent() {
	
}

public Agent(int matricule, String email, int telephone, String nom, String prenom, Date dateNaiss, String adress, String statut, String specialite)  {
	
	super(matricule,email, telephone, nom, prenom, dateNaiss,adress, statut);
	this.specialite = specialite;
}

public String getSpecialite() {
	return specialite;
}

public void setSpecialite(String specialite) {
	this.specialite = specialite;
}



}
