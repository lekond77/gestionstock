package vue;


import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.ImageIcon;

import javax.swing.border.LineBorder;

import controleur.Authentification;
import modeles.Utilisateur;
import vue.article.PanelAjouterArticle;
import vue.article.PanelConsulterArticle;
import vue.article.PanelGestionEmplacement;
import vue.article.PanelRechercheArticle;
import vue.personnelle.AjoutPersonnelPane;

import vue.personnelle.ModifierPersonnelPane;
import vue.personnelle.ConsulterPersonnelPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;


public class Acceuil extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane, panelMain, AjoutPersoPane, panelPersonnelle, panelAjouterProduit;
	private JPanel panelQuitter_1;
	JLabel lblExit;
	JLabel messageBienvenue ;
	private static JLabel userPrivilege;
	
	// Chargement des images icon utiliser sur le menu
	private Image img_acceuil = new ImageIcon(Acceuil.class.getResource("/icons/iconHome.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_ajout = new ImageIcon(Acceuil.class.getResource("/icons/ajouter.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_consulter = new ImageIcon(Acceuil.class.getResource("/icons/Consulter.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_emplacement = new ImageIcon(Acceuil.class.getResource("/icons/emplacement.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_recherche = new ImageIcon(Acceuil.class.getResource("/icons/recherche.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_parametre = new ImageIcon(Acceuil.class.getResource("/icons/parametre.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_quitter = new ImageIcon(Acceuil.class.getResource("/icons/sortie.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_personnel = new ImageIcon(Acceuil.class.getResource("/icons/gestion-user.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_modifier_user = new ImageIcon(Acceuil.class.getResource("/icons/modify_user.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_add_user = new ImageIcon(Acceuil.class.getResource("/icons/addUser.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_list_utilisateur = new ImageIcon(Acceuil.class.getResource("/icons/list-user-icon.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_logo = new ImageIcon(Acceuil.class.getResource("/icons/logo1.jpg")).getImage().getScaledInstance(190, 90, Image.SCALE_SMOOTH);
	private Image img_user = new ImageIcon(Acceuil.class.getResource("/icons/user-icon.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	
	private PanelAcceuil panelAcceuils;
	private PanelAjouterArticle panelAjout;
	private PanelConsulterArticle panelArticle;
	private PanelGestionEmplacement panelEmplacement;
	private PanelRechercheArticle PanelRechercheArticles;
	private PanelParamettre panelParamettres;
	private AjoutPersonnelPane ajoutPersonnelPane;
	private ModifierPersonnelPane modifierPersonnel;
	private ConsulterPersonnelPane supprimerPersonnel;


	/**
	 * Create the frame.
	 */
	public Acceuil() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 593);
		setUndecorated(true);
		setLocationRelativeTo(null); 
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelAcceuils = new PanelAcceuil();
		panelAcceuils.setBounds(0, 0, 671, 543);
		panelAjout = new PanelAjouterArticle();
		panelArticle = new PanelConsulterArticle();
		panelEmplacement = new PanelGestionEmplacement();
		PanelRechercheArticles = new PanelRechercheArticle();
		panelParamettres = new PanelParamettre();
		ajoutPersonnelPane = new AjoutPersonnelPane();  
		modifierPersonnel = new ModifierPersonnelPane();
		supprimerPersonnel = new ConsulterPersonnelPane();

		menuCliked(panelAcceuils);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 203, 593);
		panelMenu.setBackground(new Color(51, 102, 102));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JPanel panelAcceuil = new JPanel();
		panelAcceuil.addMouseListener(new PanelButtonMouseAdapter(panelAcceuil) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCliked(panelAcceuils);
			}
		});
		panelAcceuil.setBackground(new Color(51, 102, 102));
		panelAcceuil.setBounds(0, 96, 203, 40);
		panelMenu.add(panelAcceuil);
		panelAcceuil.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Home");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(76, 6, 97, 28);
		panelAcceuil.add(lblNewLabel);
		
		 panelAjouterProduit = new JPanel();
		panelAjouterProduit.addMouseListener(new PanelButtonMouseAdapter(panelAjouterProduit) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCliked(panelAjout);
			}
		});
		panelAjouterProduit.setBackground(new Color(51, 102, 102));
		panelAjouterProduit.setBounds(0, 136, 203, 40);
		panelMenu.add(panelAjouterProduit);
		panelAjouterProduit.setLayout(null);
		
		JLabel lblAjouterProduit = new JLabel("Ajouter Produit");
		lblAjouterProduit.setHorizontalAlignment(SwingConstants.LEFT);
		lblAjouterProduit.setForeground(Color.WHITE);
		lblAjouterProduit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAjouterProduit.setBounds(60, 6, 137, 28);
		panelAjouterProduit.add(lblAjouterProduit);
		
		JLabel lblImgAjout = new JLabel("");
		lblImgAjout.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgAjout.setBounds(7, 0, 41, 40);
		lblImgAjout.setIcon(new ImageIcon(img_ajout));
		panelAjouterProduit.add(lblImgAjout);
		
		JPanel panelConsulterProduit = new JPanel();
		panelConsulterProduit.addMouseListener(new PanelButtonMouseAdapter(panelConsulterProduit) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCliked(panelArticle);
			}
		});
		panelConsulterProduit.setBackground(new Color(51, 102, 102));
		panelConsulterProduit.setBounds(0, 176, 203, 40);
		panelMenu.add(panelConsulterProduit);
		panelConsulterProduit.setLayout(null);
		
		JLabel lblConsulterProduit = new JLabel("Consulter Produit");
		lblConsulterProduit.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsulterProduit.setForeground(Color.WHITE);
		lblConsulterProduit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblConsulterProduit.setBounds(58, 6, 139, 28);
		panelConsulterProduit.add(lblConsulterProduit);
		
		JLabel lblImgConsulter = new JLabel("");
		lblImgConsulter.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgConsulter.setBounds(6, 0, 41, 40);
		lblImgConsulter.setIcon(new ImageIcon(img_consulter));
		panelConsulterProduit.add(lblImgConsulter);
		
		JPanel PanelGestionEmplacement = new JPanel();
		PanelGestionEmplacement.addMouseListener(new PanelButtonMouseAdapter(PanelGestionEmplacement) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCliked(panelEmplacement);
			}
		});
		PanelGestionEmplacement.setBackground(new Color(51, 102, 102));
		PanelGestionEmplacement.setBounds(0, 256, 203, 40);
		panelMenu.add(PanelGestionEmplacement);
		PanelGestionEmplacement.setLayout(null);
		
		JLabel lblConsulterEmplacement = new JLabel("Gestion Emplacement");
		lblConsulterEmplacement.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsulterEmplacement.setForeground(Color.WHITE);
		lblConsulterEmplacement.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblConsulterEmplacement.setBounds(56, 6, 141, 28);
		PanelGestionEmplacement.add(lblConsulterEmplacement);
		
		JLabel lblImgEmplacement = new JLabel("");
		lblImgEmplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgEmplacement.setBounds(6, 0, 41, 40);
		lblImgEmplacement.setIcon(new ImageIcon(img_emplacement));
		PanelGestionEmplacement.add(lblImgEmplacement);
		
		JPanel panelChercherProduit = new JPanel();
		panelChercherProduit.addMouseListener(new PanelButtonMouseAdapter(panelChercherProduit) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCliked(PanelRechercheArticles);
				
				boolean estGestionnaire =  Authentification.estGestionnaire;
		
				if(estGestionnaire)
					PanelRechercheArticles.getBtnModifier().setVisible(true);
				else
					PanelRechercheArticles.getBtnModifier().setVisible(false);
					
			}
		});
		
		panelChercherProduit.setBackground(new Color(51, 102, 102));
		panelChercherProduit.setBounds(0, 216, 203, 40);
		panelMenu.add(panelChercherProduit);
		panelChercherProduit.setLayout(null);
		
		JLabel lblRechercherProduit = new JLabel("Rechercher Produit");
		lblRechercherProduit.setHorizontalAlignment(SwingConstants.LEFT);
		lblRechercherProduit.setForeground(Color.WHITE);
		lblRechercherProduit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRechercherProduit.setBounds(59, 6, 138, 28);
		panelChercherProduit.add(lblRechercherProduit);
		
		JLabel lblImgRecherche = new JLabel("");
		lblImgRecherche.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgRecherche.setBounds(6, 0, 41, 40);
		lblImgRecherche.setIcon(new ImageIcon(img_recherche));
		panelChercherProduit.add(lblImgRecherche);
		
		JPanel panelParamettre = new JPanel();
		panelParamettre.addMouseListener(new PanelButtonMouseAdapter(panelParamettre) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCliked(panelParamettres);
			}
		});
		panelParamettre.setBackground(new Color(51, 102, 102));
		panelParamettre.setBounds(0, 500, 203, 40);
		panelMenu.add(panelParamettre);
		panelParamettre.setLayout(null);
		
		JLabel lblParametre = new JLabel("Parametre");
		lblParametre.setHorizontalAlignment(SwingConstants.LEFT);
		lblParametre.setForeground(Color.WHITE);
		lblParametre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblParametre.setBounds(60, 6, 143, 28);
		panelParamettre.add(lblParametre);
		
		JLabel lblImgParametre = new JLabel("");
		lblImgParametre.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgParametre.setBounds(7, 0, 41, 40);
		lblImgParametre.setIcon(new ImageIcon(img_parametre));
		panelParamettre.add(lblImgParametre);
		
		panelQuitter_1 = new JPanel();
		panelQuitter_1.addMouseListener(new PanelButtonMouseAdapter(panelQuitter_1));
		panelQuitter_1.setBackground(new Color(51, 102, 102));
		panelQuitter_1.setBounds(0, 543, 203, 40);
		panelMenu.add(panelQuitter_1);
		panelQuitter_1.setLayout(null);
		
		JLabel lblQuitter = new JLabel("Déconnexion");
		lblQuitter.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuitter.setForeground(Color.WHITE);
		lblQuitter.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblQuitter.setBounds(57, 6, 112, 28);
		panelQuitter_1.add(lblQuitter);
		
		JLabel lblImgQuitter = new JLabel("");
		lblImgQuitter.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgQuitter.setBounds(6, 0, 41, 40);
		lblImgQuitter.setIcon(new ImageIcon(img_quitter));
		panelQuitter_1.add(lblImgQuitter);
		
		JPanel panelMenuHeader = new JPanel();
		panelMenuHeader.setBackground(new Color(51, 102, 102));
		panelMenuHeader.setBounds(201, 0, 684, 43);
		contentPane.add(panelMenuHeader);
		panelMenuHeader.setLayout(null);
		
		lblExit = new JLabel("X");
		lblExit.setBounds(640, 6, 38, 31);
		panelMenuHeader.add(lblExit);
		lblExit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panelMain = new JPanel();
		panelMain.setBounds(211, 47, 668, 540);
		contentPane.add(panelMain);
		panelMain.setLayout(null);
		
		
		// Ajout de panneaux extérieur 
		
		panelMain.add(panelAcceuils);
		panelMain.add(panelAjout);
		panelMain.add(panelArticle);
		panelMain.add(panelEmplacement);
		panelMain.add(PanelRechercheArticles);
		panelMain.add(panelParamettres);
		panelMain.add(ajoutPersonnelPane);
		panelMain.add(modifierPersonnel);
		panelMain.add(supprimerPersonnel);
		
		
		
		
		JLabel lblImgAcceuil = new JLabel("");
		lblImgAcceuil.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgAcceuil.setBounds(17, 0, 41, 40);
		lblImgAcceuil.setIcon(new ImageIcon(img_acceuil));
		panelAcceuil.add(lblImgAcceuil);
		
		
		// Gestion des personnlles
		
		panelPersonnelle = new JPanel();
		panelPersonnelle.setLayout(null);
		panelPersonnelle.setBackground(new Color(51, 102, 102));
		panelPersonnelle.setBounds(0, 296, 203, 166);
		panelMenu.add(panelPersonnelle);
		
		//Texte Personnelle
		JLabel lblPersonnelle = new JLabel("Personnel");
		lblPersonnelle.setHorizontalAlignment(SwingConstants.LEFT);
		lblPersonnelle.setForeground(Color.WHITE);
		lblPersonnelle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPersonnelle.setBounds(55, 6, 79, 28);
		panelPersonnelle.add(lblPersonnelle);
		
		//Image personnelle
		JLabel labelImgPerso = new JLabel("");
		labelImgPerso.setHorizontalAlignment(SwingConstants.CENTER);
		labelImgPerso.setBounds(6, 0, 42, 30);
		labelImgPerso.setIcon(new ImageIcon(img_personnel));
		panelPersonnelle.add(labelImgPerso);
		
		//Panel ajout personnel 
		
		AjoutPersoPane = new JPanel();
		AjoutPersoPane.setBounds(33, 44, 170, 28);
		AjoutPersoPane.setBackground(new Color(51, 102, 102));
		AjoutPersoPane.setLayout(null);
		
		//Ajout du texte 
		JLabel ajoutAgents = new JLabel("Ajouter Personnel");
		ajoutAgents.setHorizontalAlignment(SwingConstants.LEFT);
		ajoutAgents.setForeground(Color.WHITE);
		ajoutAgents.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ajoutAgents.setBounds(43, 0, 127, 28);
		AjoutPersoPane.add(ajoutAgents);
		
		//Image ajouterPersonnel
		
		JLabel lbAjouPerso = new JLabel("");
		lbAjouPerso.setHorizontalAlignment(SwingConstants.CENTER);
		lbAjouPerso.setBounds(6, 0, 30, 28);
		lbAjouPerso.setIcon(new ImageIcon(img_add_user));
		AjoutPersoPane.add(lbAjouPerso);
		
		panelPersonnelle.add(AjoutPersoPane);
		
		//evènement sur Ajout 
		
		AjoutPersoPane.addMouseListener(new PanelButtonMouseAdapter(AjoutPersoPane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCliked(ajoutPersonnelPane);
			}
		});
		
		//Edition personnel
		JPanel EditerPersoPane = new JPanel();
		EditerPersoPane.setLayout(null);
		EditerPersoPane.setBackground(new Color(51, 102, 102));
		EditerPersoPane.setBounds(33, 82, 170, 28);
		panelPersonnelle.add(EditerPersoPane);
		
		
		// Texte edition
		JLabel lblEPersonnel = new JLabel("Editer Personnel");
		lblEPersonnel.setHorizontalAlignment(SwingConstants.LEFT);
		lblEPersonnel.setForeground(Color.WHITE);
		lblEPersonnel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblEPersonnel.setBounds(43, 0, 127, 28);
		EditerPersoPane.add(lblEPersonnel);
		
		
		//evenement editer
		
		EditerPersoPane.addMouseListener(new PanelButtonMouseAdapter(EditerPersoPane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCliked(modifierPersonnel);
			}
		});
		
		//Image ajoutPerso
		JLabel lbAjouPerso_1 = new JLabel("");
		lbAjouPerso_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbAjouPerso_1.setBounds(6, 0, 30, 28);
		lbAjouPerso_1.setIcon(new ImageIcon(img_modifier_user));
		EditerPersoPane.add(lbAjouPerso_1);
		
		
		//Supprimer personnel
		JPanel SupprimerPersoPane = new JPanel();
		SupprimerPersoPane.setLayout(null);
		SupprimerPersoPane.setBackground(new Color(51, 102, 102));
		SupprimerPersoPane.setBounds(33, 120, 170, 28);
		panelPersonnelle.add(SupprimerPersoPane);
		
		
		//Texte supprimer personnel
		
		JLabel lblSupprimerPersonnel = new JLabel("Consulter Personnels");
		lblSupprimerPersonnel.setHorizontalAlignment(SwingConstants.LEFT);
		lblSupprimerPersonnel.setForeground(Color.WHITE);
		lblSupprimerPersonnel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSupprimerPersonnel.setBounds(43, 0, 127, 28);
		SupprimerPersoPane.add(lblSupprimerPersonnel);
		
		
		//Image supprimer personnel
		JLabel lbAjouPerso_1_1 = new JLabel("");
		lbAjouPerso_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbAjouPerso_1_1.setBounds(6, 0, 30, 28);
		lbAjouPerso_1_1.setBackground(new Color(255, 255, 255));
		SupprimerPersoPane.add(lbAjouPerso_1_1);
		lbAjouPerso_1_1.setIcon(new ImageIcon(img_list_utilisateur));
		
		SupprimerPersoPane.addMouseListener(new PanelButtonMouseAdapter(SupprimerPersoPane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCliked(supprimerPersonnel);
			}
		});
		
		JLabel lbllogo = new JLabel("");
		lbllogo.setIcon(new ImageIcon(img_logo));
		lbllogo.setHorizontalAlignment(SwingConstants.CENTER);
		lbllogo.setBounds(0, 0, 203, 95);
		panelMenu.add(lbllogo);
		
		
		lblExit.addMouseListener(new PanelButtonMouseAdapter(lblExit));
		
		JPanel messageAccueil = new JPanel();
		messageAccueil.setLayout(null);
		messageAccueil.setBackground(new Color(51, 102, 102));
		messageAccueil.setBounds(0, 0, 166, 30);
		panelMenuHeader.add(messageAccueil);
		
		messageBienvenue = new JLabel("");
		messageBienvenue.setHorizontalAlignment(SwingConstants.LEFT);
		messageBienvenue.setForeground(Color.WHITE);
		messageBienvenue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		messageBienvenue.setBounds(46, 0, 112, 30);
		messageAccueil.add(messageBienvenue);
		
		JLabel lbAjouPerso_1_2 = new JLabel("");
		lbAjouPerso_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbAjouPerso_1_2.setBounds(6, 0, 30, 30);
		lbAjouPerso_1_2.setIcon(new ImageIcon(img_user));
		messageAccueil.add(lbAjouPerso_1_2);
		
		userPrivilege = new JLabel("");
		userPrivilege.setHorizontalAlignment(SwingConstants.LEFT);
		userPrivilege.setForeground(new Color(51, 102, 102));
		userPrivilege.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		userPrivilege.setBounds(171, 0, 112, 30);
		panelMenuHeader.add(userPrivilege);
		
	
		for (Component component : panelMenu.getComponents()) {
		    if (component instanceof JPanel) {
		        JPanel panel = (JPanel) component;
		        
		        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    }
		}
		
	}
	
	public static JLabel getUserPrivilege() {
		return userPrivilege;
	}
	public JLabel getWelcomField() {
		return messageBienvenue;
	}
	
	public JPanel getPanelMain() {
		return panelMain;
	}
	
	/*public JPanel getAjoutAgentPane() {
		return AjoutPersoPane;
	}*/
	
	public JPanel getAjoutArticlePane() {
		return  panelAjouterProduit;
	}
	
	public JPanel getPanelPersonnel() {
		return panelPersonnelle;
	}
	
	
	public void menuCliked(JPanel panel) {
		panelAcceuils.setVisible(false);
		panelAjout.setVisible(false);
		panelArticle.setVisible(false);
		panelEmplacement.setVisible(false);
		PanelRechercheArticles.setVisible(false);
		panelParamettres.setVisible(false);
		ajoutPersonnelPane.setVisible(false);
		modifierPersonnel.setVisible(false);
		supprimerPersonnel.setVisible(false);
		
		panel.setVisible(true);
	}
	
	//Panel déconnexion
	
		public JPanel getlogOutPanel() {
			return panelQuitter_1;
		}
	
	//Evènepment sur le bouton de fermeture et de déconnexion
		
	private class PanelButtonMouseAdapter implements MouseListener{

		JLabel jLabel;
		JPanel panel;

		public PanelButtonMouseAdapter(JLabel jLabel) {
			this.jLabel = jLabel;
		}
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == jLabel)
			if (JOptionPane.showConfirmDialog(null, "Etes vous sûr(e) de vouloir quitter l'application", "Confirmer",
					JOptionPane.YES_NO_OPTION) == 0) {
				Acceuil.this.dispose();
				
			}	
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == panel)
			panel.setBackground(new Color(69, 179, 123));
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource() == panel)
			panel.setBackground(new Color(112, 128, 144));
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == jLabel)
				jLabel.setForeground(Color.red);
			if(e.getSource() == panel)
				panel.setBackground(new Color(112, 128, 144));
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == jLabel)
				jLabel.setForeground(Color.gray);
			if(e.getSource() == panel)
				panel.setBackground(new Color(47, 79, 79));
		}	
	}
}
