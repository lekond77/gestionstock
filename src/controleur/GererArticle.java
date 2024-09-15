package controleur;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modeles.MyConBD;
import modeles.article.Article;
import modeles.article.Entrepot;
import modeles.article.Etagere;
import modeles.article.GestionArticle;
import modeles.article.Stock;
import modeles.article.Zone;

public class GererArticle {
	GestionArticle gestionArticle = new GestionArticle();
	private Connection connexion;
	
	public GererArticle() {

		connexion = MyConBD.getInstance().getConnection();
		gestionArticle.setConnexion(connexion);
	}
	
	public boolean isDataIncomplete(Article article, Stock stock) {

		if (article.getReferenceArticle().isEmpty() || article.getNomArticle().isEmpty()
				|| article.getPrixArticle() == 0 || article.getDateCreation() == null
				|| article.getDescriptionArticle().isEmpty()||stock.getQuantiteStock()==0) {
			return true;
		}
		return false;
	}
	// Appel a methode pour executer l'insertion d'un article
	public void exAjouterArticle(Article article,Entrepot entrepot,Zone zone, Etagere etagere, Stock stock)throws Exception {
		if (article != null) {
			boolean isIncomplete = (isDataIncomplete(article, stock));
			
			if (isIncomplete == true)
				throw new Exception("Tous les champs sont réquis ");
			// Appel a la methode pour ajouter un article et le stock
			boolean art = gestionArticle.ajouterArticle(article);
			boolean stk = gestionArticle.ajouterStock(article, entrepot,zone,etagere, stock);
			if (art && stk) {
				JOptionPane.showMessageDialog(null, "L'article - reference : " + article.getReferenceArticle()
						+ " a été ajouté avec succès !");
			} else {
				throw new Exception("Une erreur est survenue lors de l'insertion ");
			}
		}else {
			throw new Exception("Les données sont invalides ");
		}
	}
	
	// Appel a la methode pour faire la recherche d'un article
	public Article exRechercherArticle(String reference)throws Exception {
		Article article = new Article();
		if(reference.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Veuillez renseigner la reference du produit");
		}
		else {
			// Appel a la methode pour rechercher un article en fonction du reference de l'article
			article = gestionArticle.rechercherArticle(reference);
			
		}
		
		return article;
	}
	
	public Stock exRechercherStock(String article) throws Exception {
		Stock stock =  new Stock();
		// Appel a la methode pour recherche pour stock d'un article
		stock = gestionArticle.rechercherStock(article);
		
		return stock;
	}
	// Appel methode pour modifier un article et le stock de l'article
	public void exModifierArticle(Article article, Stock stock) throws Exception{
		if (article != null) {
			boolean isIncomplete = (isDataIncomplete(article, stock));

			if (isIncomplete == true)
				throw new Exception("Tous les champs sont réquis ");
			// Appel aux methode de modification des donnees d'un article et son stock
			boolean resultArticle = gestionArticle.modifierArticle(article);
			boolean resultStock = gestionArticle.modifierStock(stock);

			if (resultArticle && resultStock) {
				JOptionPane.showMessageDialog(null, "Les données de l'article - reference : " + article.getReferenceArticle()
						+ " ont été modifiées avec succés!");
			} else {
				throw new Exception("Une erreur est survenue lors de la modification ");
			}
		} else
			throw new Exception("Les données sont invalides ");
	}
	// Methode pour executer la supprression d'un article
	public void exSupprimerArticle(int idArticle) throws Exception {
		// Appel la mehtode pour supprimer un article 
		boolean estSupprimer = gestionArticle.supprimerArticle(idArticle);
		
		if(estSupprimer) {
			JOptionPane.showMessageDialog(null, "l'article - reference : " + idArticle
			+ " est supprimer");
		}else {
			throw new Exception("Une erreur est survenue lors de la suppression de l'article" + idArticle);
			
		}
	}
	// Exectue la selection des articles enregistre dans la base
	public List<Article> exAfficherArticle() throws Exception {
		// Appel a la meethode pour afficher la liste des article
		List<Article> list = gestionArticle.afficherArticle();
		
		return list;
		
	}
	// Execute l'affichage des tout les entrepots
	public List <Entrepot> exAfficherEntrepots() throws Exception{
		// Appel a la mehtode pour afficher la list des entrepots 
		List <Entrepot> list = gestionArticle.afficherEntrepots();
		
		return list;
	}
	
	// Execute l'affichade des zone pour un entrepot donnee
	public List <Zone> exAfficherZones(String codeEntrepot) throws Exception{
		// Appel a la mehtode pour afficher les zone d'un entrepot
		List <Zone> list = gestionArticle.afficherZones(codeEntrepot);
		
		return list;
	}
	// Execute l'affichage des etagere pour une zone x en fonction de son code 
	public List <Etagere> exAfficherEtageres(String codeZone) throws Exception{
		// Appel a la methode pour afficher les etagere d'un zone 
		List <Etagere> list = gestionArticle.afficherEtagere(codeZone);
		
		return list;
	}
	// Execute l'affichage de tout les informations pour un article
	public List<Stock> exInfoArticles() throws Exception{
		// Appel a la methode pour afficher les information d'un article
		List<Stock> list = gestionArticle.infoArticles();
		
		return list;
	}
	// Execute la recuperation du code d'un entrepot
	public String [] exGetCodeEntrepots() throws Exception{
		int i =0;
		String[] entrepots= new String [gestionArticle.getCodeEntrepots().size()];
		for (String entrepot : gestionArticle.getCodeEntrepots()) {
			entrepots[i] = entrepot;
			i++;
		}
		
		return entrepots;
	}
	/*******
	 * recuperation du code d'un zones
	 * 
	 * @throws Exception
	 ********/
	public String [] exGetGetCodeZones(String codeEntrepot) throws Exception{
		int i =0;
		String[] zones= new String [gestionArticle.getCodeZones(codeEntrepot).size()];
		for (String zone : gestionArticle.getCodeZones(codeEntrepot)) {
			zones[i] = zone;
			i++;
		}
		
		return zones;
	}
	
	// Methode qui recupere le code de l'etagere en fonction qui appartient a une zone specifique 
	// En fonction du code de la zone auquelle elle est ratacher
	public String [] exGetGetCodeEtagere(String codeZone) throws Exception{
		int i =0;
		String[] etageres= new String [gestionArticle.getCodeEtagere(codeZone).size()];
		for (String etagere : gestionArticle.getCodeEtagere(codeZone)) {
			etageres[i] = etagere;
			i++;
		}
		
		return etageres;
	}
	
	
}
