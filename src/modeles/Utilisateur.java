package modeles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import modeles.personnel.Personnelle;

public class Utilisateur extends Personnelle{

	private String motDePasse;
	Random random = new Random();
	private Connection con;
	private Integer cle;
	private String motDePasseBis;
	private boolean privilege;
	private boolean estGestionnaire = false;

	public boolean estGestionnaire() {
		return estGestionnaire;
	}

	public void setEstGestionnaire(boolean estGestionnaire) {
		this.estGestionnaire = estGestionnaire;
	}

	public String getMotDePasseBis() {
		return motDePasseBis;
	}

	public void setMotDePasseBis(String motDePasseBis) {
		this.motDePasseBis = motDePasseBis;
	}

	public Integer getCle() {
		return cle;
	}

	public void setCle(Integer cle) {
		this.cle = cle;
	}

	public Utilisateur(String email, String motDePasse) {
		super(email);
		this.motDePasse = motDePasse;

	}
	
	public Utilisateur(String email, String motDePasse, String motDePasseBis) {
		super(email);
		this.motDePasse = motDePasse;
		this.motDePasseBis = motDePasseBis;

	}

	public Utilisateur(String email, int cle) {
		super(email);
		this.cle  = cle;
	}
	
	
	public Utilisateur() {

	}

	public Utilisateur(String email) {
		super(email);
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setPassword(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean aPrivilege() {
		return privilege;
	}

	public void setPrivilege(boolean privilege) {
		this.privilege = privilege;
	}
	
	// Permet de définir une instance de base de données
	public void setCon(Connection con) {
		this.con = con;
	}

	// Authentification
	public Utilisateur seConnecter(Utilisateur utilisateur) throws Exception {

		Utilisateur utilisateurConnecte = new Utilisateur();

		ResultSet donneesUtilisateur = null;
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = con.prepareStatement("SELECT * FROM utilisateur WHERE email = ? AND motDePasse = ?;");

			preparedStatement.setString(1, utilisateur.getEmail());
			preparedStatement.setString(2, utilisateur.getMotDePasse());

			donneesUtilisateur = preparedStatement.executeQuery();

			if(donneesUtilisateur.next()) {

				utilisateurConnecte.setNom(donneesUtilisateur.getString("nom"));
				utilisateurConnecte.setPrenom(donneesUtilisateur.getString("prenom"));
				utilisateurConnecte.setEmail(donneesUtilisateur.getString("email"));
				utilisateurConnecte.setPrivilege(donneesUtilisateur.getBoolean("privilege"));
				utilisateurConnecte.setEstGestionnaire(donneesUtilisateur.getBoolean("estGestionnaire"));
				
			}
			

		} catch (SQLException e) {

			MyConBD.rollback(con);
			throw new Exception(e.getMessage());

		} finally {
			MyConBD.closeResultSet(donneesUtilisateur);
			MyConBD.closePreparedStatement(preparedStatement);
		}

		return utilisateurConnecte;
	}

	
	// Vérifie si l'utilisateur est present dans la base et génère un code

		public int verifierEmail(Utilisateur utilisateur) throws Exception {

			PreparedStatement preparedStatement = null;

			int cleGenere = 100000 + random.nextInt(900000); // générer un code aléatoire
			int ligneAffecte = 0;

			try {

				preparedStatement = con.prepareStatement("UPDATE utilisateur set cle = ?  WHERE email = ?");
				preparedStatement.setInt(1, cleGenere);
				preparedStatement.setString(2, utilisateur.getEmail());
				ligneAffecte = preparedStatement.executeUpdate();

				if (ligneAffecte == 0) {
					throw new SQLException("Désolé. Nous n'avons pas pu vous identifier avec le e-mail fourni.");
				}

			} catch (SQLException e) {
				MyConBD.rollback(con);
				throw new Exception(e.getMessage());
			} finally {
				
				MyConBD.closePreparedStatement(preparedStatement);
			}
			return cleGenere;
		}
	
	
	// Verifier la clé

		public Utilisateur verifierCle(Utilisateur utilisateur) throws Exception {

			Utilisateur utilisateurCle = new Utilisateur();
			
			ResultSet donneesCle = null;

			PreparedStatement preparedStatement = null;

			try {

				preparedStatement = con.prepareStatement("SELECT * FROM utilisateur WHERE email = ? AND cle = ?");
				preparedStatement.setString(1, utilisateur.getEmail());
				preparedStatement.setInt(2, utilisateur.getCle());

				donneesCle = preparedStatement.executeQuery();

				while (donneesCle.next()) {
					
					utilisateurCle.setEmail(donneesCle.getString("email")); 
					utilisateurCle.setCle(donneesCle.getInt("cle")); 
				}

			} catch (SQLException e) {
				
				MyConBD.rollback(con);
				throw new Exception(e.getMessage());
				
			} finally {
				
				MyConBD.closeResultSet(donneesCle);
				MyConBD.closePreparedStatement(preparedStatement);
			}
			return utilisateurCle;
		}

	
	// Changer mot de passe
		public boolean changerMotDepasse(Utilisateur utilisateur) throws Exception {
			
			utilisateur.setCle(0);
			PreparedStatement preparedStatement = null;
			int ligneAffectee = 0;

			try {

				preparedStatement = con.prepareStatement("UPDATE utilisateur set cle = ?, motDePasse = ?  WHERE email = ?");

				preparedStatement.setInt(1, utilisateur.getCle());
				preparedStatement.setString(2, utilisateur.getMotDePasse());
				preparedStatement.setString(3, utilisateur.getEmail());

				ligneAffectee = preparedStatement.executeUpdate();
				

			} catch (SQLException e) {

				throw new Exception(e.getMessage());
			} finally {
				
			}
			return ligneAffectee > 0;
		}

		
}
