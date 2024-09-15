package modeles.personnel;

import java.util.Date;

public class Gestionnaire extends Personnelle{
	private int idGestionnaire;
	private int numService;
	
	public Gestionnaire() {
		super();
	}
	
	public Gestionnaire(int matricule, String email, int telephone, String nom, String prenom, Date dateNaiss, String adress, String statut, int numService)  {
		
		super(matricule,email, telephone, nom, prenom, dateNaiss,adress, statut);
		this.numService = numService;
	}
	
	public int getIdGestionnaire() {
		return idGestionnaire;
	}
	public void setIdGestionnaire(int idGestionnaire) {
		this.idGestionnaire = idGestionnaire;
	}
	public int getNumService() {
		return numService;
	}
	public void setNumService(int numService) {
		this.numService = numService;
	}

	

}
