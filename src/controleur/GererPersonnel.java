package controleur;

import java.sql.Connection;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import modeles.MyConBD;
import modeles.personnel.Agent;
import modeles.personnel.GestionPersonnelle;
import modeles.personnel.Gestionnaire;
import modeles.personnel.Personnelle;
import vue.Acceuil;


public class GererPersonnel {

	GestionPersonnelle gestionPerso = new GestionPersonnelle();
	private Connection connexion;
	JButton ajouterAgent, validerModifs;
	
	public GererPersonnel() {
		connexion = MyConBD.getInstance().getConnection();
		gestionPerso.setConnexion(connexion);
	}

	/***************** Insertion Agent **********************/
	// Vérifier s'il y a de champs non remplis

	public boolean isDataIncomplete(Personnelle dataPersonnel) {

		if (dataPersonnel.getEmail().isEmpty() || dataPersonnel.getNom().isEmpty()
				|| dataPersonnel.getPrenom().isEmpty() || dataPersonnel.getAdress().isEmpty()
				|| dataPersonnel.getStatut().isEmpty() || dataPersonnel.getDateNaiss() == null) {
			return true;
		}
		return false;
	}

	public void executeAjoutAgent(Agent dataAgent) throws Exception {
		
		if (dataAgent != null) {

			boolean isIncomplete = (isDataIncomplete(dataAgent) || dataAgent.getSpecialite().isEmpty());

			if (isIncomplete == true)
				throw new Exception("Tous les champs sont réquis ");

			boolean result = gestionPerso.ajouterAgent(dataAgent);
			if (result) {
				
				JOptionPane.showMessageDialog(null,
						"L'agent - Matricule : " + dataAgent.getMatricule() + " a été ajouté avec succès !");
			} else {
				throw new Exception("Une erreur est survenue lors de l'insertion ");
			}
		} else {
			throw new Exception("Les données sont invalides ");
		}
	}

	/***************** Insertion Gestionnaire **********************/

	public void executeAjoutGestionnaire(Gestionnaire dataGestionnaire) throws Exception {

		if (dataGestionnaire != null) {

			boolean isIncomplete = (isDataIncomplete(dataGestionnaire));

			if (isIncomplete == true)
				throw new Exception("Tous les champs sont réquis ");

			boolean result = gestionPerso.ajouterGestionnaire(dataGestionnaire);
			if (result) {
				JOptionPane.showMessageDialog(null, "Le gestionnaire - Matricule : " + dataGestionnaire.getMatricule()
						+ " a été ajouté avec succès !");
			} else {
				throw new Exception("Une erreur est survenue lors de l'insertion ");
			}
		} else {
			throw new Exception("Les données sont invalides ");
		}
	}

	/****** Listes des personnels ****/

	/***** Listes des agents ***********/
	public List<Agent> getAgents() throws Exception {

		List<Agent> listAgent = gestionPerso.afficherAgents();

			return listAgent;
	}

	// Récuperer les informations de tous les agents et les afficher dans la vue

	public String[] listComboBoxAgent(List<Agent> listAgent) throws Exception {

		String[] comboItems = new String[listAgent.size()];

		for (int i = 0; i < listAgent.size(); i++) {

			Agent agent = listAgent.get(i);
			String comboBoxItem = agent.getMatricule() + " - " + agent.getNom().toUpperCase() + " - "
					+ agent.getPrenom().toUpperCase() + " - " + agent.getEmail().toUpperCase() + " - "
					+ agent.getTelephone() + " - " + agent.getStatut() + " - " + agent.getSpecialite();
			comboItems[i] = comboBoxItem;

		}

		return comboItems;

	}

	// Afficher les informations d'un agent lorqu'une recherche est faite

	public Agent afficherInfosAgent(List<Agent> agents, int matricule) {

		for (Agent agent : agents) {

			if (agent.getMatricule() == matricule)
				return agent;
		}
		return null;
	}

	/********* Listes des gestionnaires ********/

	public List<Gestionnaire> getGestionnaires() throws Exception {

		List<Gestionnaire> listGestionnaire = gestionPerso.afficherGestionnaires();

		//if (listGestionnaire != null && listGestionnaire.size() > 0)

			return listGestionnaire;

		//return null;
	}

	// Récuperer les informations du gestionnaire et les afficher dans la vue

	public String[] listComboBoxGestionnaire(List<Gestionnaire> listGestionnaire) throws Exception {

		String[] comboItems = new String[listGestionnaire.size()];

		for (int i = 0; i < listGestionnaire.size(); i++) {

			Gestionnaire gestionnaire = listGestionnaire.get(i);
			String comboBoxItem = gestionnaire.getMatricule() + " - " + gestionnaire.getNom().toUpperCase() + " - "
					+ gestionnaire.getPrenom().toUpperCase() + " - " + gestionnaire.getEmail().toUpperCase() + " - "
					+ gestionnaire.getTelephone() + " - " + gestionnaire.getStatut() + " - "
					+ gestionnaire.getNumService();

			comboItems[i] = comboBoxItem;

		}

		return comboItems;
	}

	// Afficher les informations d'un gestionnaire lorqu'une recherche est faite
	public Gestionnaire afficherInfosGestionnaire(List<Gestionnaire> gestionnaires, int matricule) {

		for (Gestionnaire gestionnaire : gestionnaires) {

			if (gestionnaire.getMatricule() == matricule)
				return gestionnaire;
		}
		return null;
	}

	/***** Modifications des personnels *******/

	/********** Modifier un agent *********/

	public void modifierAgent(Agent donnesAgent) throws Exception {

		if (donnesAgent != null) {
			boolean isIncomplete = (isDataIncomplete(donnesAgent) || donnesAgent.getSpecialite().isEmpty());

			if (isIncomplete == true)
				throw new Exception("Tous les champs sont réquis ");

			boolean result = gestionPerso.modifierAgent(donnesAgent);

			if (result) {
				JOptionPane.showMessageDialog(null, "Les données de l'agent - Matricule : " + donnesAgent.getMatricule()
						+ " ont été modifiées avec succés!");
			} else {
				throw new Exception("Une erreur est survenue lors de la modification ");
			}
		} else
			throw new Exception("Les données sont invalides ");
	}

	/******* Modifier gestionnaires ******/

	public void modifierGestionnaire(Gestionnaire donnesGestionnaire) throws Exception {

		if (donnesGestionnaire != null) {

			boolean isIncomplete = (isDataIncomplete(donnesGestionnaire));

			if (isIncomplete == true)
				throw new Exception("Tous les champs sont réquis ");

			// Apple de la méthode modifierGestionnaire du modèle
			// On stock le résultat dans result qu'on utilisera pour vérifier si les données
			// ont été vraiment modifier
			boolean result = gestionPerso.modifierGestionnaire(donnesGestionnaire);

			if (result) {
				JOptionPane.showMessageDialog(null, "Les données du gestionnaire - Matricule : "
						+ donnesGestionnaire.getMatricule() + " ont été modifiées avec succés!");
			} else {
				throw new Exception("Une erreur est survenue lors de la modification ");
			}
		} else
			throw new Exception("Les données sont invalides ");
	}

	/*******
	 * Les status disponibles
	 * 
	 * @throws Exception
	 ********/

	public String[] getStatut() throws Exception {

		int i = 0;

		// On stocke les statut dans un tableaau de String
		String[] statuts = new String[gestionPerso.statutPossible().size()];
		for (String statut : gestionPerso.statutPossible()) {
			statuts[i] = statut;
			i++;
		}

		return statuts;
	}

	/***
	 * supprimer
	 * 
	 * @throws Exception
	 ****/

	public void supprimerPersonnel(int matricule) throws Exception {

		boolean miseAJour = gestionPerso.supprimerPersonnel(matricule);

		if (!miseAJour)
			throw new Exception("Une erreur est survenue lors de la suppression du personnel" + matricule);
	}

}
