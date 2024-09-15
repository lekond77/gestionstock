package modeles.article;

public class Zone {
	private int idZone;
	private String codeZone;
	private String descriptionZone;
	
	public Zone(int idZone, String codeZone, String descriptionZone) {
		super();
		this.idZone = idZone;
		this.codeZone = codeZone;
		this.descriptionZone = descriptionZone;
	}
	
	public Zone(String codeZone, String descriptionZone) {
		super();
		this.codeZone = codeZone;
		this.descriptionZone = descriptionZone;
	}
	

	public Zone(String codeZone) {
		super();
		this.codeZone = codeZone;
	}

	public Zone() {
		super();
	}
	public int getIdZone() {
		return idZone;
	}
	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}
	public String getCodeZone() {
		return codeZone;
	}
	public void setCodeZone(String codeZone) {
		this.codeZone = codeZone;
	}
	public String getDescriptionZone() {
		return descriptionZone;
	}
	public void setDescriptionZone(String descriptionZone) {
		this.descriptionZone = descriptionZone;
	}

	@Override
	public String toString() {
		return "Zone " + codeZone;
	}
	

}
