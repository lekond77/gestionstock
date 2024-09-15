package main;

import controleur.Authentification;
import vue.authentification.ConnexionPane;

public class Main {

	public static void main(String[] args) throws Exception {

		try {

			ConnexionPane frame = new ConnexionPane();
			Authentification pageAthentification = new Authentification(frame);

			frame.setVisible(true);

			// Connecter l'utilisateur
			pageAthentification.connecterUtilisateur();

			// Affiche la fenetre pour renseigner l'é-mail pour le mot de passe oublié

			pageAthentification.afficherFormulaireChangerPass();

			// Afiicher le formulaire de changement de passe
			pageAthentification.afficherFormulaire();

			//Annuler changement passe oublie 

			pageAthentification.annulerMotDePassOublie();

			// Envoie de email pour réinitialiser le mot de passe
			pageAthentification.sendEmail();

			// Retour à la page d'authentification
			pageAthentification.backToConnection();

			// Changer le pass

			pageAthentification.motDePasseChange();

			// Deconnexion

			pageAthentification.deconnexion();
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
