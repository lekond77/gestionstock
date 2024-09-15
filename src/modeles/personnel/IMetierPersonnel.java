package modeles.personnel;

import java.util.List;

public interface IMetierPersonnel {

	public boolean supprimerPersonnel(int matricule) throws Exception;
	public boolean ajouterGestionnaire(Gestionnaire gestionnaire) throws Exception;
	public boolean modifierGestionnaire(Gestionnaire gestionnaire) throws Exception;
	public List<Gestionnaire> afficherGestionnaires() throws Exception;
	public boolean ajouterAgent(Agent agent) throws Exception;
	public boolean modifierAgent(Agent agent) throws Exception;
	public List<Agent> afficherAgents() throws Exception;
	public List<String> statutPossible() throws Exception;
}
