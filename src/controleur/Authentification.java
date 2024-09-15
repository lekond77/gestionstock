package controleur;

import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeles.MyConBD;
import modeles.Utilisateur;
import vue.Acceuil;
import vue.authentification.ConnexionPane;
import vue.authentification.DemandeChangePassPane;
import vue.authentification.FormulaireClePane;
import vue.authentification.FormulaireMiseAJourPane;
import vue.authentification.Mail;
import vue.authentification.PassChangePane;

public class Authentification {
	private Utilisateur utilisateur = new Utilisateur();
	private ConnexionPane frame;
	DemandeChangePassPane updatePassFrame = new DemandeChangePassPane();
	private JPanel mainPanel, logOut, panelPersonnel, panelAjoutArticle;
	FormulaireMiseAJourPane formUpdate = new FormulaireMiseAJourPane();
	Acceuil acceuil = new Acceuil();
	PassChangePane succesUpdate = new PassChangePane();
	FormulaireClePane formulaireCle = new FormulaireClePane();
	private CardLayout cards;
	private Mail mail;
	Connection connexion;
	JButton refreshBtn, btnDemandeEnvoye, changePass, backBtn, btnEnvoyerCle, annulerBtn, annulerBtnCle;
	JTextField getEmail;
	public static boolean estGestionnaire = false;
	

	public Authentification(ConnexionPane frame) {

		connexion = MyConBD.getInstance().getConnection();
		utilisateur.setCon(connexion);
		this.frame = frame;
		mainPanel = this.frame.getMainPanel();
		refreshBtn = formulaireCle.getRefreshBtn();
		btnDemandeEnvoye = updatePassFrame.getEnvoyerEmail();
		btnEnvoyerCle = formulaireCle.getEnvoyerCle();
		changePass = formUpdate.getChangePass();
		getEmail = formUpdate.getEmail();
		backBtn = succesUpdate.getBackBtn();
		annulerBtn = updatePassFrame.getButtonAnnuler();
		annulerBtnCle = formulaireCle.getButtonAnnuler();
		logOut = acceuil.getlogOutPanel();
		panelPersonnel = acceuil.getPanelPersonnel();
		panelAjoutArticle = acceuil.getAjoutArticlePane();
		mail = new Mail();
	}

	public Authentification() {

	}
	// Utilisation de CardLayout pour contenir les panels à afficher selon les
	// cliques

	private CardLayout cardPanel() {
		cards = new CardLayout();
		mainPanel.setLayout(cards);
		mainPanel.add(updatePassFrame, "ChampEmail");
		mainPanel.add(formUpdate, "formulaire");
		mainPanel.add(succesUpdate.getContentPane(), "PassChangé");
		mainPanel.add(frame.getLoginPanel(), "Login");
		mainPanel.add(succesUpdate.getContentPane(), "SuccesChange");
		mainPanel.add(formulaireCle, "cle");

		return cards;
	}

	// Vérifier les données saisies par l'utilisateur

	private void execute(Utilisateur donneesConnexion) throws Exception {

		Utilisateur utilisateurConnecte;

		if (donneesConnexion != null) {

			String identifier = donneesConnexion.getEmail();
			String password = donneesConnexion.getMotDePasse();

			if (identifier.isEmpty() || password.isEmpty())

				throw new Exception("Les données renvoyées sont invalides");
		}

		// Appel de la fonction seConnecter depuis Utilisateur(model) pour récupérer les
		// données de l'utilisateur, si trouvées

		utilisateurConnecte = utilisateur.seConnecter(donneesConnexion);

		if (utilisateurConnecte.getEmail() == null || utilisateurConnecte.getNom() == null
				|| utilisateurConnecte.getPrenom() == null || utilisateurConnecte.getEmail().isEmpty())
			throw new Exception("L'identifiant ou le mot de passe est incorrect !");

		// On ferme la page LoginPage et on ouvre la page Acceuil

		if (utilisateurConnecte.aPrivilege()) {

			// Si l'utilisateur est admin
			panelPersonnel.setVisible(true);
			panelAjoutArticle.setVisible(true);
			estGestionnaire = true;

		} else {
			panelPersonnel.setVisible(false);
			if (utilisateurConnecte.estGestionnaire()) {
				// Si l'utilisateur est gestionnaire
				panelAjoutArticle.setVisible(true);
				estGestionnaire = true;
			} else {
				// Si l'utilisateur est agent
				panelAjoutArticle.setVisible(false);
				estGestionnaire = false;
			}
		}

		acceuil.setVisible(true);
		frame.dispose();

		String nom = utilisateurConnecte.getNom();
		// Affiche le nom de l'utilisateur à la page d'acceuil
		acceuil.getWelcomField().setText(
				String.valueOf(nom.charAt(0)).toUpperCase() + ".  " + utilisateurConnecte.getPrenom().toUpperCase());
		Acceuil.getUserPrivilege().setText(String.valueOf(utilisateurConnecte.aPrivilege()));

	}

	public void connecterUtilisateur() {

		frame.getLogin().addMouseListener(new MouseAdapter() { // Ajout d'évènement lorsque se connecter appuyer
			public void mouseClicked(MouseEvent e) {
				try {

					// Appel de la fonction execute(Utilisateur) pour connecter l'utilisateur ou
					// renvoyer un message d'erreur
					execute(frame.utilisateurDemandeConnexion());

				} catch (Exception e1) {

					frame.getErrorMessage().setText(e1.getMessage());
				}
			}
		});
	}

	// Gestion de mot de passe oublié

	public void afficherFormulaireChangerPass() {
		frame.eventToUpdatePass().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// Choisir le panel correspondant au renseignement de mot de passe
				cardPanel();
				cards.show(mainPanel, "ChampEmail");
			}
		});
	}

	// Envoie de la clé générée par email pour changer mot de passe

	private void verifierEmail(Utilisateur utilisateurEmail) throws Exception {

		if (utilisateurEmail != null) {
			if (utilisateurEmail.getEmail().isEmpty())

				throw new Exception("Les données envoyées sont invalides");
		}

		int cle = utilisateur.verifierEmail(utilisateurEmail);

		// Envoie de mail
		 mail.mailSended(utilisateurEmail.getEmail(), cle);

		formulaireCle.getEmailField().setText(utilisateurEmail.getEmail()); // Remplit le champ de email

		JOptionPane.showMessageDialog(null,
				"Un email contenant une clé unique à été envoyé à l'adresse: " + utilisateurEmail.getEmail());

		System.out.println("Message envoyé: code -> " + cle
				+ "\nEnlever commentaire ligne 182(controller/Authentification.java)\nRemplacer email ligne 17-18(vue/authentification/Mail.java)\nActiver SMTP");

	}

	// Vérifier la clé pour afficher le formulaire de changement de mot de passe
	public void verifierCle(Utilisateur cleUtilisateur) throws Exception {

		Utilisateur utilisateurCle;

		if (cleUtilisateur != null) {
			if (cleUtilisateur.getEmail().isEmpty())
				throw new Exception("Les données envoyées sont invalides");
		}

		utilisateurCle = utilisateur.verifierCle(cleUtilisateur);

		if (utilisateurCle.getEmail() == null || utilisateurCle.getEmail().isEmpty())

			throw new Exception("Désolé. Une erreur innatendue s'est produite.\n"
					+ "Nous ne pouvons pas changer votre mot de passe");

		formUpdate.getEmail().setText(utilisateurCle.getEmail());

	}

	// Afficher formulaire qui sera appelé dans la main
	public void afficherFormulaire() {

		btnEnvoyerCle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int cleSaisi = Integer.parseInt(formulaireCle.getCle().getText());

					// verifierCle(updatePassFrame.getCleChangerPass());
					verifierCle(formulaireCle.getCleChangerPass());

					cardPanel();
					cards.show(mainPanel, "formulaire");

				} catch (NumberFormatException ex) {
					// ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Oup ! Vérifier les saisies !");
				} catch (Exception e1) {
					// e1.printStackTrace();
					formulaireCle.getErrorMessage().setText(e1.getMessage());
				}

			}

		});
	}

	// Envoie de email
	public void sendEmail() {
		
		btnDemandeEnvoye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					verifierEmail(updatePassFrame.getEmailChangerPass());

					// Choix du panel correspondant au champ de email pour le changment de mot de
					// passe
					cardPanel();
					cards.show(mainPanel, "cle");

				} catch (Exception e1) {
					// e1.printStackTrace();
					updatePassFrame.getErrorMessage().setText(e1.getMessage());
				}

			}
		});

		// Regénérer le code
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// verifierEmail(updatePassFrame.getCleChangerPass());
					verifierEmail(formulaireCle.getCleChangerPass());

				} catch (Exception e1) {

					updatePassFrame.getErrorMessage().setText(e1.getMessage());
				}
			}
		});

	}

	// Changer le mot de passe
	private void changerPass(Utilisateur utilisateurPassChanger) throws Exception {

		if (utilisateurPassChanger != null) {

			if (utilisateurPassChanger.getEmail().isEmpty() || utilisateurPassChanger.getMotDePasse().isEmpty()
					|| utilisateurPassChanger.getMotDePasseBis().isEmpty())

				throw new Exception("Les données renvoyées sont invalides !");

			if (!utilisateurPassChanger.getMotDePasse().equals(utilisateurPassChanger.getMotDePasseBis()))

				throw new Exception("Erreur! Les mots de passe ne sont pas identiques !");
		}

		boolean passChange = utilisateur.changerMotDepasse(utilisateurPassChanger);

		if (passChange) {
			cardPanel();
			cards.show(mainPanel, "SuccesChange");

		} else {
			throw new Exception("Désolé. Nous ne pouvons pas changer votre mot de passe.");
		}

	}

	// Méthode de changement de mot de passe lorque le bouton changePass sera cliqué
	public void motDePasseChange() {

		changePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					changerPass(formUpdate.getMotDePasseChanger());

				} catch (Exception e1) {

					formUpdate.getErrorMessage().setText(e1.getMessage());
				}
			}
		});
	}

//Retour à la page de connexion
	public void backToConnection() {
		backBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				// Choisir le panel de login lorsque retour à l'acceuil cliqué
				cardPanel();
				cards.show(mainPanel, "Login");
			}
		});
	}

	// Annuler l'opération de changement de mot de pass

	public void annulerMotDePassOublie() {
		annulerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Choisir le panel de login lorsque retour à l'acceuil cliqué
				cardPanel();
				cards.show(mainPanel, "Login");
			}
		});

		annulerBtnCle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Choisir le panel de login lorsque retour à l'acceuil cliqué
				cardPanel();
				cards.show(mainPanel, "ChampEmail");
			}
		});

	}

	// Déconnexion
	public void deconnexion() {

		logOut.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (JOptionPane.showConfirmDialog(null, "Confirmer la déconnexion", "Déconnexion",
						JOptionPane.YES_NO_OPTION) == 0) {
					acceuil.dispose();
					acceuil.setVisible(false);
					frame.setVisible(true);
					frame.getPassword().setText("");

				}
			}
		});
	}
}
