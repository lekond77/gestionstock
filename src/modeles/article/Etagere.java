package modeles.article;

public class Etagere {
	private int idEtagere;
	private int numeroEtage;
	private String codeEtagere;
	

	public Etagere() {
		super();
	}
	
	public Etagere(int idEtagere, int numeroEtage, String codeEtagere) {
		super();
		this.idEtagere = idEtagere;
		this.numeroEtage = numeroEtage;
		this.codeEtagere = codeEtagere;
	}
	
	public Etagere(String codeEtagere) {
		super();
		this.codeEtagere = codeEtagere;
	}

	public Etagere(int idEtagere) {
		super();
		this.idEtagere = idEtagere;
	}

	public int getIdEtagere() {
		return idEtagere;
	}
	public void setIdEtagere(int idEtagere) {
		this.idEtagere = idEtagere;
	}
	public String getCodeEtagere() {
		return codeEtagere;
	}
	public void setCodeEtagere(String codeEtagere) {
		this.codeEtagere = codeEtagere;
	}
	public int getNumeroEtage() {
		return numeroEtage;
	}
	public void setNumeroEtage(int numeroEtage) {
		this.numeroEtage = numeroEtage;
	}

	@Override
	public String toString() {
		return "Etagere " + numeroEtage;
	}
	
}
