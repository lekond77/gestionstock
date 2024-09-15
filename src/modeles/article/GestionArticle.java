package modeles.article;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import modeles.MyConBD;
import vue.article.PanelRechercheArticle;

public  class GestionArticle implements IMetiersArticle{
	
	private Connection connexion;
	private String message;

//Initialisé une connexion à la base de données

	public void setConnexion(Connection connexion) {
		this.connexion = connexion;
		message = "Une erreur s'est produite lors de l'insertion des données : ";
	}
	/*
	 * Redefinition de la methode ajouterArticle declarer dans l'interface IMetiersArticles
	 * Cette methode nous permet d'inserer un article dans la base de donnee
	 * */
	@Override
	public boolean ajouterArticle(Article article) throws Exception {
		int ligneAffectee = 0;
		PreparedStatement preparedStatement = null;

		connexion.setAutoCommit(false); 
		
		try {
		// Requette d'insertion d'un article
		String reqAjouterArticle = "INSERT INTO Articles (refArticle,nomArticle,dateCreation,prixArticle,description)"+ "VALUES (?,?,?,?,?)";
		
		preparedStatement = connexion.prepareStatement(reqAjouterArticle);
		preparedStatement.setString(1, article.getReferenceArticle());
		preparedStatement.setString(2, article.getNomArticle());
		preparedStatement.setDate(3, (Date) article.getDateCreation());
		preparedStatement.setInt(4, article.getPrixArticle());
		preparedStatement.setString(5, article.getDescriptionArticle());
		
		ligneAffectee += preparedStatement.executeUpdate();
		
		if (ligneAffectee == 1)
			connexion.commit(); // Valide la transaction
		
		}catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur

			throw new Exception(message + e.getMessage());

		} finally {

			MyConBD.closePreparedStatement(preparedStatement);

		}
		
		
		return true;
	}
	/*
	 * Redefinition dela methode qui nous permet de faire la rechercher d'un article en fonction de sa reference
	 * */
	
	@Override
	public Article rechercherArticle(String reference) {
		try {
			// Requette de selection d'un article par sareference
			String reqRechercherArticle = "SELECT * FROM Stock INNER JOIN Articles ON stock.article = Articles.refArticle WHERE refArticle = ?";
			PreparedStatement prstm = connexion.prepareStatement(reqRechercherArticle);
			prstm.setString(1, reference);
			
			ResultSet res = prstm.executeQuery();
			
			Article article = new Article();
			Stock st = new Stock();
			PanelRechercheArticle pr = new PanelRechercheArticle();
			while(res.next()) {
				//JOptionPane.showMessageDialog(null, "Article trouvé");
				
				article.setIdArticle(res.getInt("idArticle"));
				article.setReferenceArticle(res.getString("refArticle"));
				article.setNomArticle(res.getString("nomArticle"));
				article.setPrixArticle(res.getInt("prixArticle"));
				article.setDateCreation(res.getDate("dateCreation"));
				article.setDescriptionArticle(res.getString("description"));
				
			}
			return article;
			
		} catch (SQLException ex) {
			Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Article non trouvé");
		}
		
		return null;
	}
	
	/*
	 * Redefinition de la methode demodification d'un article
	 * CetteMethode prend en paramettre un un objet article
	 * il mettra a jour les information de l'article dans la base de donnee
	 * */
	@Override
	public boolean modifierArticle(Article article) throws Exception {
		
		PreparedStatement preparedStatement = null;
		
		try {
			// Requette de mis a jour des champs de la table article 
		 String reqModifier = "UPDATE Articles set nomArticle = ?, dateCreation = ?, prixArticle = ? , description = ? WHERE refArticle = ?";
		
		 	preparedStatement = connexion.prepareStatement(reqModifier);
		 	preparedStatement.setString(1, article.getNomArticle());
		 	preparedStatement.setDate(2, (Date) article.getDateCreation());
		 	preparedStatement.setInt(3, article.getPrixArticle());
		 	preparedStatement.setString(4, article.getDescriptionArticle());
		 	preparedStatement.setString(5,article.getReferenceArticle());
		 	
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur

			throw new Exception(message + e.getMessage());

		} finally {

			MyConBD.closePreparedStatement(preparedStatement);

		}
		
		return true;
	}
	
	/*
	 * Redefinitons de la methode de suppression d'un article
	 * Cette methode permet de supprimer un article dans la base de donnee
	 * il prend en paramaettre l'identifiant de l'article qui sera supprimer
	 * */
	@Override
	public boolean supprimerArticle(int idArticle) throws Exception {
		PreparedStatement preparedStatement = null;
		
		try {
			// Requette de suppresson d'un article
			String reqSupprimer = "DELETE FROM Articles WHERE idArticle = ?";
			preparedStatement = connexion.prepareStatement(reqSupprimer);
			preparedStatement.setInt(1, idArticle);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur
			throw new Exception(e.getMessage());

		} finally {

			MyConBD.closePreparedStatement(preparedStatement);
		}
		return true;
	}
	/*
	 * Redefinition de la methode qui retour un list d'article
	 * Cette methode permet d'afficher la liste des articles enregistrer dans la base de donnee*/
	@Override
	public List<Article> afficherArticle() throws Exception {
		List<Article> list = new ArrayList<>();
		
		try {
			// Requuete de selection des articles et le stock relier des articles
			String reqAfficherArticle = "SELECT * FROM Stock INNER JOIN Articles ON stock.article = Articles.refArticle";
			Statement stm = connexion.createStatement();
			ResultSet res = stm.executeQuery(reqAfficherArticle);
			while(res.next()) {
				Article article = new Article();
				Entrepot ent =  new Entrepot();
				article.setIdArticle(res.getInt("idArticle"));
				article.setReferenceArticle(res.getString("refArticle"));
				article.setNomArticle(res.getString("nomArticle"));
				article.setPrixArticle(res.getInt("prixArticle"));
				article.setDateCreation(res.getDate("dateCreation"));
				article.setDescriptionArticle(res.getString("description"));
				
				list.add(article);
			}
		} catch (SQLException ex) {
			Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return list;
	}
	/*
	 * Redefinition de la methode pour ajouter un stock
	 * Cette methode sera utiliser pour ajouter un stock dans la able stock
	 * Ausssi de reliser cette stock a un article 
	 * Definir l'emplacement et l'etagere de se stock
	 * il prend en paramettre des objet d'article , l'emplacement, la zone et le stock
	 * */
	@Override
	public boolean ajouterStock(Article article, Entrepot entrepot, Zone zone, Etagere etagere, Stock stock) throws Exception {
		int ligneAffectee = 0;
		PreparedStatement preparedStatement = null;
		
		try {
		connexion.setAutoCommit(false); 
		// Requete d'insertion d'un stock 
		String reqInsertStock = "INSERT INTO Stock (article, entrepot, zone, etagere, quantiteStock)"+"VALUES (?,?,?,?,?)";
		
		preparedStatement = connexion.prepareStatement(reqInsertStock);
		preparedStatement.setString(1, article.getReferenceArticle());
		preparedStatement.setString(2, entrepot.getcodeEntrepot());
		preparedStatement.setString(3, zone.getCodeZone());
		preparedStatement.setString(4, etagere.getCodeEtagere());
		preparedStatement.setInt(5, stock.getQuantiteStock());
		
		ligneAffectee += preparedStatement.executeUpdate();
		
		if (ligneAffectee == 1)
			connexion.commit(); // Valide la transaction
		}catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur

			throw new Exception(message + e.getMessage());

		} finally {

			MyConBD.closePreparedStatement(preparedStatement);

		}
		return true;
	}
	
	/*
	 * Methode de modification d'un stock
	 * */
	@Override
	public boolean modifierStock(Stock stock) throws Exception {
		PreparedStatement preparedStatement = null;

		try {
			//, entrepot = ?, zone = ?, etagere = ?
		String reqModifierEntrepot = "UPDATE stock set quantiteStock = ? WHERE article = ?";
		preparedStatement = connexion.prepareStatement(reqModifierEntrepot);
		preparedStatement.setInt(1, stock.getQuantiteStock());
		/*preparedStatement.setString(2, stock.getEntrepot());
		preparedStatement.setString(3, stock.getZone());
		preparedStatement.setString(4, stock.getEtagere());*/
		preparedStatement.setString(2, stock.getArticle());
		
		preparedStatement.executeUpdate();
		}
		catch (SQLException e) {

			MyConBD.rollback(connexion); // Annuler la transaction en cours en cas d'erreur

			throw new Exception(message + e.getMessage());

		} finally {

			MyConBD.closePreparedStatement(preparedStatement);

		}
		return true;
	}
	/*
	 * Permet de faire la rechercher du stock d'un article*/
	@Override
	public Stock rechercherStock(String article) throws Exception {
		try {
			// Reqquette de selection
			String reqRechercherArticle = "SELECT * FROM Stock INNER JOIN Articles ON stock.article = Articles.refArticle WHERE refArticle = ?";
			PreparedStatement prstm = connexion.prepareStatement(reqRechercherArticle);
			prstm.setString(1, article);
			
			ResultSet res = prstm.executeQuery();
			
			Stock stock = new Stock();
			PanelRechercheArticle pr = new PanelRechercheArticle();
			while(res.next()) {
				
				stock.setIdStock(res.getInt("idStock"));
				stock.setEntrepot(res.getString("entrepot"));
				stock.setQuantiteStock(res.getInt("quantiteStock"));
				
				
			}
			return stock;
			
		} catch (SQLException ex) {
			Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Article non trouvé");
		}
		
		return null;
	}
	
	/*
	 * Redefinition de la methode pour afficjer la liste des entrepot
	 * Cette methode afficher la liste de tout les entrepots
	 * */
	@Override
	public List<Entrepot> afficherEntrepots() throws Exception {
		List<Entrepot> list = new ArrayList<>();
		
		try {
			//
			String reqAfficherEntrepots = "SELECT * FROM Stock INNER JOIN Entrepot ON stock.entrepot = Entrepot.codeEntrepot";
			Statement stm = connexion.createStatement();
			ResultSet res = stm.executeQuery(reqAfficherEntrepots);
			while(res.next()) {
				
				Entrepot entrepot = new Entrepot();
				entrepot.setIdEntrepot(res.getInt("idEntrepot"));
				entrepot.setcodeEntrepot(res.getString("codeEntrepot"));
				entrepot.setLibelleEntrepot(res.getString("libelleEntrepot"));
				
				list.add(entrepot);
			}
			
		} catch (SQLException ex) {
				Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		return list;
	}
	
	/*
	 * Cette methode afficher la liste des zone specifique a un entrepot
	 * Il nous permet d'identite les zone qui appartient a un entrepot x en fonction du code de l'entrepot
	 * */
	@Override
	public List<Zone> afficherZones(String codeEntrepot) throws Exception {
		List<Zone> list = new ArrayList<>();
		
		try {
			// Requette joincture de la table zone et entrepot pour recuperer les zone par le code de l'entrepot
			String reqAfficherZone = "SELECT * FROM Zone INNER JOIN Entrepot ON Entrepot.codeEntrepot = Zone.entrepot  where entrepot = ?";
			PreparedStatement pstm = connexion.prepareStatement(reqAfficherZone);
			pstm.setString(1, codeEntrepot);
			
			ResultSet res = pstm.executeQuery();
			while(res.next()) {
				
				Zone zone = new Zone();
				zone.setCodeZone(res.getString("codeZone"));
				zone.setDescriptionZone(res.getString("desciptionZone"));
				
				list.add(zone);
			}
		} catch (SQLException ex) {
			Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}
	/*
	 * Redefinition de la methode pour afficher la liste des etagere pour une zone donner
	 * Il envoi une liste des etgare composant un zone en fonction du code de la zone
	 * */
	@Override
	public List<Etagere> afficherEtagere(String codeZone) throws Exception {
		List<Etagere> list = new ArrayList<>();
		
		try {
			String reqAfficherEtagere = "SELECT * FROM Etagere INNER JOIN Zone ON Zone.codeZone = Etagere.zone where codeZone = ?";
			PreparedStatement stm = connexion.prepareStatement(reqAfficherEtagere);
			stm.setString(1, codeZone);
			ResultSet res = stm.executeQuery();
			while(res.next()) {
				
				Etagere etagere = new Etagere();
				etagere.setIdEtagere(res.getInt("idEtagere"));
				etagere.setNumeroEtage(res.getInt("numeroEtage"));
				etagere.setCodeEtagere(res.getString("codeEtagere"));
				
				list.add(etagere);
			}
		} catch (SQLException ex) {
			Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}
	/*
	 * Cette methode nous permet de recuperer le code d'un l'entrepot
	 * */
	@Override
	public List<String> getCodeEntrepots() throws Exception {
		List<String> list = new ArrayList<>();
		
		try {
			String reqAfficherEntrepots = "SELECT * FROM Entrepot";
			Statement stm = connexion.createStatement();
			ResultSet res = stm.executeQuery(reqAfficherEntrepots);
			while(res.next()) {
				
				String codeEntrepot = res.getString("codeEntrepot");
				//String libelleEntrepot = res.getString("libelleEntrepot");
				
				
				list.add(codeEntrepot);
				//list.add(libelleEntrepot);
			}
		} catch (SQLException ex) {
			Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}
	/*
	 * Cette methode nous permet de recuperer le code d'un zone d'un entrepot
	 * En fonction du code de l'entrepot
	 * */
	@Override
	public List<String> getCodeZones(String codeEntrepot)  {
		List<String> list = new ArrayList<>();
		
		try {
			
			String reqCodeZone = "SELECT * FROM Zone INNER JOIN Entrepot ON Entrepot.codeEntrepot = Zone.entrepot  where entrepot = ?";
			PreparedStatement pstm = connexion.prepareStatement(reqCodeZone);
			pstm.setString(1, codeEntrepot);
			ResultSet res = pstm.executeQuery();
			
			while(res.next()) {
				
				String codeZones = res.getString("codeZone");
				//String libelleEntrepot = res.getString("libelleEntrepot");
				
				
				list.add(codeZones);
				//list.add(libelleEntrepot);
			}
		} catch (SQLException ex) {
			Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}
	/*
	 * Cette methode nous permet de recuperer le code d'un etagere 
	 * En fonction du code de la zone
	 * */
	@Override
	public List<String> getCodeEtagere(String codeZone) throws Exception {
		List<String> list = new ArrayList<>();
		
		try {
			
			String reqCodeEtagere = "SELECT * FROM Etagere INNER JOIN Zone ON Zone.codeZone = Etagere.zone where codeZone = ?";
			PreparedStatement pstm = connexion.prepareStatement(reqCodeEtagere);
			pstm.setString(1, codeZone);
			ResultSet res = pstm.executeQuery();
			
			while(res.next()) {
				
				String codeEtagere = res.getString("codeEtagere");
				//String libelleEntrepot = res.getString("libelleEntrepot");
				
				
				list.add(codeEtagere);
				//list.add(libelleEntrepot);
			}
		} catch (SQLException ex) {
			Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}
	
	/*
	 * Cette Methode afficher les information d'un article
	 * */
	public List<Stock> infoArticles() {
		List<Stock> list = new ArrayList<>();
		
		try {
			
			String infoArticle = "SELECT * FROM Stock";
			Statement stm = connexion.createStatement();
			ResultSet res = stm.executeQuery(infoArticle);
			while(res.next()) {
				
				Stock stock = new Stock();
				//Stock.(res.getInt("idEntrepot"));
				stock.setZone(res.getString("zone"));
				stock.setEtagere(res.getString("etagere"));
				
				list.add(stock);
			}
			
		} catch (SQLException ex) {
				Logger.getLogger(MyConBD.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		return list;
	}
	
}
