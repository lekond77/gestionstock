package modeles.article;

public class Entrepot {
	private int idEntrepot;
	private String codeEntrepot;
	private String libelleEntrepot;
	
	public Entrepot() {
		super();
	}
	
	

	public Entrepot(String codeEntrepot) {
		super();
		this.codeEntrepot = codeEntrepot;
	}



	public Entrepot(String codeEntrepot, String libelleEntrepot) {
		super();
		this.codeEntrepot = codeEntrepot;
		this.libelleEntrepot = libelleEntrepot;
	}
	

	public Entrepot(int idEntrepot, String codeEntrepot, String libelleEntrepot) {
		super();
		this.idEntrepot = idEntrepot;
		this.codeEntrepot = codeEntrepot;
		this.libelleEntrepot = libelleEntrepot;
	}
	
	public int getIdEntrepot() {
		return idEntrepot;
	}
	public void setIdEntrepot(int idEntrepot) {
		this.idEntrepot = idEntrepot;
	}
	public String getcodeEntrepot() {
		return codeEntrepot;
	}
	public void setcodeEntrepot(String codeEntrepot) {
		this.codeEntrepot = codeEntrepot;
	}
	public String getLibelleEntrepot() {
		return libelleEntrepot;
	}
	public void setLibelleEntrepot(String libelleEntrepot) {
		this.libelleEntrepot = libelleEntrepot;
	}
	
	@Override
	public String toString() {
		return libelleEntrepot;
	}
	
}
