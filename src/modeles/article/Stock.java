package modeles.article;

public class Stock {
	private int idStock;
	private String article;
	private String entrepot;
	private String zone;
	private String etagere;
	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getEtagere() {
		return etagere;
	}

	public Stock(int quantiteStock,String article) {
		super();
		this.quantiteStock = quantiteStock;
		this.article = article;
	}

	public void setEtagere(String etagere) {
		this.etagere = etagere;
	}
	private int quantiteStock;
	
	public Stock(int idStock, String article, String entrepot, int quantiteStock) {
		super();
		this.idStock = idStock;
		this.article = article;
		this.entrepot = entrepot;
		this.quantiteStock = quantiteStock;
	}

	public Stock(String article, String entrepot, int quantiteStock) {
		super();
		this.article = article;
		this.entrepot = entrepot;
		this.quantiteStock = quantiteStock;
	}
	
	public Stock(int quantiteStock, String entrepot, String article, String zone, String etagere) {
		super();
		this.quantiteStock = quantiteStock;
		this.entrepot = entrepot;
		this.article = article;
		this.zone = zone;
		this.etagere = etagere;
	}
	

	public Stock(int quantiteStock) {
		super();
		this.quantiteStock = quantiteStock;
	}

	public Stock() {
		super();
	}

	public int getIdStock() {
		return idStock;
	}
	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getEntrepot() {
		return entrepot;
	}
	public void setEntrepot(String entrepot) {
		this.entrepot = entrepot;
	}
	public int getQuantiteStock() {
		return quantiteStock;
	}
	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}

}
