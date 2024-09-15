package modeles.article;

import java.util.Date;

public class Article {
	private int idArticle;
	private String referenceArticle;
	private String nomArticle;
	private Date dateCreation;
	private int prixArticle;
	private String descriptionArticle;
	
	public Article(int idArticle) {
		super();
		this.idArticle = idArticle;
	}

	public Article() {
		super();
	}

	public Article(int idArticle, String referenceArticle, String nomArticle, Date dateCreation, int prixArticle,
			String descriptionArticle) {
		super();
		this.idArticle = idArticle;
		this.referenceArticle = referenceArticle;
		this.nomArticle = nomArticle;
		this.dateCreation = dateCreation;
		this.prixArticle = prixArticle;
		this.descriptionArticle = descriptionArticle;
	}

	public Article(String referenceArticle, String nomArticle, Date dateCreation, int prixArticle,
			String descriptionArticle) {
		super();
		this.referenceArticle = referenceArticle;
		this.nomArticle = nomArticle;
		this.dateCreation = dateCreation;
		this.prixArticle = prixArticle;
		this.descriptionArticle = descriptionArticle;
	}

	public Article(String nomArticle, Date dateCreation, int prixArticle, String descriptionArticle,
			String referenceArticle) {
		super();
		this.nomArticle = nomArticle;
		this.dateCreation = dateCreation;
		this.prixArticle = prixArticle;
		this.descriptionArticle = descriptionArticle;
		this.referenceArticle = referenceArticle;
	}

	public Article(String referenceArticle) {
		super();
		this.referenceArticle = referenceArticle;
	}



	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getReferenceArticle() {
		return referenceArticle;
	}
	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	
	public int getPrixArticle() {
		return prixArticle;
	}

	public void setPrixArticle(int prixArticle) {
		this.prixArticle = prixArticle;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDescriptionArticle() {
		return descriptionArticle;
	}
	public void setDescriptionArticle(String descriptionArticle) {
		this.descriptionArticle = descriptionArticle;
	}

	@Override
	public String toString() {
		return referenceArticle + " " + nomArticle + " "
				+ prixArticle + " " + dateCreation ;
	}
}
