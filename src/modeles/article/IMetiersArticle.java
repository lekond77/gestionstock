package modeles.article;

import java.util.List;

/*
 * Interface regroupant l'ensemble des methode pour la gestion des article
 * Ces methode seront redefini
 * */public interface IMetiersArticle {
	/*
	 * Ensemble des methode pour la gestion des article 
	 * */
	public boolean ajouterArticle(Article article) throws Exception;
	public boolean modifierArticle(Article article) throws Exception;
	public boolean supprimerArticle(int reference) throws Exception;
	public List<Article> afficherArticle() throws Exception;
	public Article rechercherArticle(String reference);
	
	public boolean ajouterStock(Article article,Entrepot entrepot, Zone zone, Etagere etagere, Stock stock) throws Exception;
	public boolean modifierStock(Stock stock) throws Exception;
	public Stock rechercherStock(String article) throws Exception;
	

	public List <String> getCodeEntrepots() throws Exception;
	public List <String> getCodeZones(String codeEntrepot) ;
	public List <String> getCodeEtagere(String codeZone) throws Exception;

	public List <Entrepot> afficherEntrepots() throws Exception;
	public List <Zone> afficherZones(String codeEntrepot) throws Exception;
	public List <Etagere> afficherEtagere(String codeZone) throws Exception;
	
	
}
