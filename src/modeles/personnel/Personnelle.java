package modeles.personnel;

import java.util.Date;

public abstract class Personnelle {
	private int matricule;
	private String nom;
	private String prenom;
	private Date dateNaiss;
	private int telephone;
	private String email;
	
	public Personnelle() {
		
	}
	
	public Personnelle(int matricule, String email, int telephone, String nom, String prenom, Date dateNaiss, String adress, String statut) {
		this.matricule = matricule;
		this.email = email;
		this.telephone = telephone;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = dateNaiss;
		this.adress = adress;
		this.statut = statut;
	}
	
	public Personnelle(String email) {
		this.email = email;
	}

	public int getMatricule() {
		return matricule;
	}
	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date  getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaissance) {
		this.dateNaiss =  dateNaissance;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	private String adress;
	private String statut;

}
