package modeles.personnel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modeles.MyConBD;
import vue.Acceuil;

public class GestionPersonnelle implements IMetierPersonnel{

	private Connection connexion;
	private String message = "Une erreur s'est produite lors de l'insertion des données : ";
	
   //Initialisé une connexion à la base de données

	public void setConnexion(Connection connexion) {
		this.connexion = connexion;
	}
	
	private void aPrivilege() throws Exception{
		
		if(Acceuil.getUserPrivilege().getText().equals("false"))
			throw new Exception("Accès refusé ! ");
	}

	/**** Methode d'ajout, de mofication et de suppression sur un personnelle 
	 * @throws Exception ****/

	// Insérer un Personnel

	private PreparedStatement ajouterPersonnel(Personnelle personnel) throws SQLException, Exception {
		
		aPrivilege();

		PreparedStatement preparedStatement = null;

		connexion.setAutoCommit(false); // Désactive la validation automatique de la transaction

		// Ajout  des informations communes des personnels
		String query = "INSERT INTO utilisateur (matricule, email, nom, prenom, dateNaissance, adresse, motDePasse, telephone, statut)"
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		preparedStatement = connexion.prepareStatement(query);
		preparedStatement.setInt(1, personnel.getMatricule());
		preparedStatement.setString(2, personnel.getEmail());
		preparedStatement.setString(3, personnel.getNom());
		preparedStatement.setString(4, personnel.getPrenom());
		preparedStatement.setDate(5, (new java.sql.Date(personnel.getDateNaiss().getTime())));
		preparedStatement.setString(6, personnel.getAdress());
		preparedStatement.setString(7, "");
		preparedStatement.setInt(8, personnel.getTelephone());
		preparedStatement.setString(9, personnel.getStatut());

		return preparedStatement;
	}

	// Modifier un personnelle

	private PreparedStatement modifierPersonnel(Personnelle personnel) throws SQLException, Exception {

		aPrivilege();
		
		PreparedStatement preparedStatement = null;

		connexion.setAutoCommit(false); // Désactive la validation automatique de la transaction

		// Mise à jour des données d'un personnel
		String query = "UPDATE utilisateur SET email = ?,adresse = ?,telephone = ?, statut = ? WHERE matricule = ?";

		preparedStatement = connexion.prepareStatement(query);
		preparedStatement.setString(1, personnel.getEmail());
		preparedStatement.setString(2, personnel.getAdress());
		preparedStatement.setInt(3, personnel.getTelephone());
		preparedStatement.setString(4, personnel.getStatut());
		preparedStatement.setInt(5, personnel.getMatricule());

		return preparedStatement;
	}
	
	//Supprimer un personnel
	
	public boolean supprimerPersonnel(int matricule) throws Exception {

		aPrivilege();
		
		PreparedStatement preparedStatement = null;
		int ligneAffectee = 0;
		
		try {

			preparedStatement = connexion.prepareStatement("DELETE FROM utilisateur WHERE matricule = ?");
			preparedStatement.setInt(1, matricule);
			
			ligneAffectee = preparedStatement.executeUpdate();
		} catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur
			throw new Exception(e.getMessage());

		} finally {

			MyConBD.closePreparedStatement(preparedStatement);
		}
		return ligneAffectee == 1;
	}
	
	
	/*********Insertion, modification, listes des agents et des gestionnaires******/

//Insérer un gestionnaire

	public boolean ajouterGestionnaire(Gestionnaire gestionnaire) throws Exception {

		int ligneAffectee = 0;

		PreparedStatement preparedStatement1 = null, preparedStatement2 = null, preparedStatement3 = null;

		try {

			connexion.setAutoCommit(false); // Désactive la validation automatique de la transaction

			// Appel de la méthode private ajouterPersonnel pour ajouter les informations
			// communns à un agent et à un gestionnaire
			preparedStatement1 = ajouterPersonnel(gestionnaire);

			ligneAffectee = preparedStatement1.executeUpdate();

			// Insertion dans la table gestionnaire
			String query2 = "INSERT INTO gestionnaire (numGestionnaire, numService)" + "VALUES (?,?)";

			preparedStatement2 = connexion.prepareStatement(query2);
			preparedStatement2.setInt(1, gestionnaire.getMatricule());
			preparedStatement2.setInt(2, gestionnaire.getNumService());

			ligneAffectee += preparedStatement2.executeUpdate();
			
			//Mettre estGestionnaire à true
			
			preparedStatement3 = connexion.prepareStatement("UPDATE utilisateur set estGestionnaire = true WHERE matricule = ?");
			
			preparedStatement3.setInt(1,  gestionnaire.getMatricule());
			ligneAffectee += preparedStatement3.executeUpdate();
			

			if (ligneAffectee == 3)
				connexion.commit(); // Valide la transaction

		} catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur

			throw new Exception(message + e.getMessage());

		} finally {

			MyConBD.closePreparedStatement(preparedStatement1);
			MyConBD.closePreparedStatement(preparedStatement2);

		}

		return ligneAffectee == 3;
	}

	//Mofier les informations d'un gestionnaire
	public boolean modifierGestionnaire(Gestionnaire gestionnaire) throws Exception {

		PreparedStatement preparedStatement1 = null, preparedStatement2 = null;

		int ligneAffectee = 0;

		try {

			connexion.setAutoCommit(false); // Désactive la validation automatique de la transaction

			preparedStatement1 = modifierPersonnel(gestionnaire);

			ligneAffectee = preparedStatement1.executeUpdate();

			// Mettre à jour  la table gestionnaire
			String query2 = "UPDATE gestionnaire SET numService = ? WHERE numGestionnaire = ?";

			preparedStatement2 = connexion.prepareStatement(query2);
			preparedStatement2.setInt(1, gestionnaire.getNumService());
			preparedStatement2.setInt(2, gestionnaire.getMatricule());

			ligneAffectee += preparedStatement2.executeUpdate();

			if (ligneAffectee == 2)
				connexion.commit(); // Valide la transaction

		} catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur

			throw new Exception(message + e.getMessage());

		} finally {

			MyConBD.closePreparedStatement(preparedStatement1);
			MyConBD.closePreparedStatement(preparedStatement2);

		}

		return ligneAffectee == 2;

	}

	// Afficher la listes des gestionnaires

	public List<Gestionnaire> afficherGestionnaires() throws Exception {
		
		// instanciation d'une list pour ajouter chaque gestionnaire
		List<Gestionnaire> listGestionnaire = new ArrayList<Gestionnaire>();

		ResultSet dataGestionnaire = null;

		try {
			Statement statement = connexion.createStatement();
			dataGestionnaire = statement
					.executeQuery("SELECT * FROM utilisateur u, gestionnaire g WHERE u.matricule = g.numGestionnaire");

			while (dataGestionnaire.next()) {
				Gestionnaire gestionnaire = new Gestionnaire();
				gestionnaire.setMatricule(dataGestionnaire.getInt("matricule"));
				gestionnaire.setNom(dataGestionnaire.getString("nom"));
				gestionnaire.setPrenom(dataGestionnaire.getString("prenom"));
				gestionnaire.setEmail(dataGestionnaire.getString("email"));
				gestionnaire.setStatut(dataGestionnaire.getString("statut"));
				gestionnaire.setTelephone(dataGestionnaire.getInt("telephone"));
				gestionnaire.setDateNaiss(dataGestionnaire.getDate("dateNaissance"));
				gestionnaire.setNumService(dataGestionnaire.getInt("numService"));
				gestionnaire.setAdress(dataGestionnaire.getString("adresse"));

				listGestionnaire.add(gestionnaire);
			}
		} catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur
			throw new Exception(e.getMessage());

		} finally {

			MyConBD.closeResultSet(dataGestionnaire);
		}
		return listGestionnaire;
	}

	// Ajouter un agent
	public boolean ajouterAgent(Agent agent) throws Exception {

		int ligneAffectee = 0;

		PreparedStatement preparedStatement1 = null, preparedStatement2 = null;

		try {

			connexion.setAutoCommit(false); // Désactive la validation automatique de la transaction

			// Appel de la méthode private ajouterPersonnel pour ajouter les informations
			// communns à un agent et à un gestionnaire
			preparedStatement1 = ajouterPersonnel(agent);

			ligneAffectee = preparedStatement1.executeUpdate();

			// Insertion dans la table Agent
			String query2 = "INSERT INTO agent (numAgent, specialite)" + "VALUES (?,?)";

			preparedStatement2 = connexion.prepareStatement(query2);
			preparedStatement2.setInt(1, agent.getMatricule());
			preparedStatement2.setString(2, agent.getSpecialite());

			ligneAffectee += preparedStatement2.executeUpdate();

			if (ligneAffectee == 2)
				connexion.commit(); // Valide la transaction

		} catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur

			throw new Exception(message + e.getMessage());

		} finally {

			MyConBD.closePreparedStatement(preparedStatement1);
			MyConBD.closePreparedStatement(preparedStatement2);

		}

		return ligneAffectee == 2;
	}

	//Modifier les informations d'un agent
	
	public boolean modifierAgent(Agent agent) throws Exception {
		int ligneAffectee = 0;

		PreparedStatement preparedStatement1 = null, preparedStatement2 = null;

		try {

			connexion.setAutoCommit(false); // Désactive la validation automatique de la transaction

			// Modification dans la table utilisateur
			// Appel de la méthode private ajouterPersonnel pour ajouter les informations
			// communns à un agent et à un gestionnaire
			preparedStatement1 = modifierPersonnel(agent);

			ligneAffectee = preparedStatement1.executeUpdate();

			// Modification dans la table agent
			String query2 = "UPDATE agent set specialite = ? WHERE numAgent = ?";

			preparedStatement2 = connexion.prepareStatement(query2);
			preparedStatement2.setString(1, agent.getSpecialite());
			preparedStatement2.setInt(2, agent.getMatricule());

			ligneAffectee += preparedStatement2.executeUpdate();

			if (ligneAffectee == 2)
				connexion.commit(); // Valide la transaction

		} catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur

			throw new Exception(message + e.getMessage());

		} finally {

			MyConBD.closePreparedStatement(preparedStatement1);
			MyConBD.closePreparedStatement(preparedStatement2);

		}

		return ligneAffectee == 2;

	}

	// Afficher les informations des agents
	
	public List<Agent> afficherAgents() throws Exception {
		
		// instanciation d'une list pour ajouter chaque agent
		List<Agent> listAgent = new ArrayList<Agent>();

		ResultSet dataAgent = null;

		try {
			Statement statement = connexion.createStatement();
			dataAgent = statement.executeQuery("SELECT * FROM utilisateur u, agent a WHERE u.matricule = a.numAgent");

			while (dataAgent.next()) {
				Agent agent = new Agent();

				agent.setMatricule(dataAgent.getInt("matricule"));
				agent.setNom(dataAgent.getString("nom"));
				agent.setPrenom(dataAgent.getString("prenom"));
				agent.setEmail(dataAgent.getString("email"));
				agent.setStatut(dataAgent.getString("statut"));
				agent.setTelephone(dataAgent.getInt("telephone"));
				agent.setDateNaiss(dataAgent.getDate("dateNaissance"));
				agent.setSpecialite(dataAgent.getString("specialite"));
				agent.setAdress(dataAgent.getString("adresse"));

				listAgent.add(agent);
			}

		} catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur
			throw new Exception(e.getMessage());

		} finally {

			MyConBD.closeResultSet(dataAgent);
		}
		return listAgent;
	}
	
	// Afficher les status
	public List<String> statutPossible() throws Exception {

		List<String> statuts = new ArrayList<String>();
		ResultSet dataDispo = null;
		try {

			Statement statement = connexion.createStatement();

			dataDispo = statement.executeQuery("SELECT nomStatut FROM statut");

			while (dataDispo.next()) {
				String statut = dataDispo.getString("nomStatut").toUpperCase();
				statuts.add(statut);
			}

		} catch (SQLException e) {
			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur

			throw new Exception(e.getMessage());
		} finally {

			MyConBD.closeResultSet(dataDispo);
		}

		return statuts;

	}

}
