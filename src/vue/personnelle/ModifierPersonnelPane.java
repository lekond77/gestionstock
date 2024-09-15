package vue.personnelle;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import controleur.GererPersonnel;
import modeles.personnel.Agent;
import modeles.personnel.Gestionnaire;

public class ModifierPersonnelPane extends JPanel {

	private JTextField text1;
	JComboBox<String> suggestionPerso, statut;

	// public JButton validerModifs;
	JPanel panel;
	private JTextField telephone, adresse, specialite, naissance, prenom, email, nom, matricule;
	private JButton enregisterModifs;
	JPanel panelEdition;
	private ButtonModel btnRadioSelectionne;
	ButtonGroup group;
	Agent agent = new Agent();
	Gestionnaire gestionnaire = new Gestionnaire();

	GererPersonnel personnel = new GererPersonnel();

	/**
	 * Create the panel.
	 */
	public ModifierPersonnelPane() {
		
		//Mettre le contenu de JcomboBox à vide lorsque aucun personnel n'est choisi
		suggestionPerso = new JComboBox<String>();

		// Afficher les informations des personnelles après recherche
		suggestionPerso.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String chaineCombo = (String) suggestionPerso.getSelectedItem();
				
				if (chaineCombo != null) {
					String[] spitChaine = chaineCombo.split(" - ");
					try {
						int matriculeSaisi = Integer.parseInt(spitChaine[0]);
						
						Agent agent = personnel.afficherInfosAgent(personnel.getAgents(), matriculeSaisi);
						Gestionnaire gestionnaire = personnel.afficherInfosGestionnaire(personnel.getGestionnaires(),
								matriculeSaisi);

						if (agent != null) {

							// Remplir les champs lorsque l'agent est cherché

							DefaultComboBoxModel<String> statutAgent = new DefaultComboBoxModel<String>();
							statutAgent.addElement(agent.getStatut());
							email.setText(agent.getEmail());
							matricule.setText(String.valueOf(agent.getMatricule()));
							nom.setText(agent.getNom());
							prenom.setText(agent.getPrenom());
							adresse.setText(agent.getAdress());
							telephone.setText(String.valueOf(agent.getTelephone()));
							specialite.setText(agent.getSpecialite());
							naissance.setText(agent.getDateNaiss().toString());

						}

						if (gestionnaire != null) {

							// Remplir les champs lorsque le gestionnaire est cherché

							DefaultComboBoxModel<String> statutGestionnaire = new DefaultComboBoxModel<String>();
							statutGestionnaire.addElement(gestionnaire.getStatut());
							email.setText(gestionnaire.getEmail());
							matricule.setText(String.valueOf(gestionnaire.getMatricule()));
							nom.setText(gestionnaire.getNom());
							prenom.setText(gestionnaire.getPrenom());
							telephone.setText(String.valueOf(gestionnaire.getTelephone()));
							specialite.setText(String.valueOf(gestionnaire.getNumService()));
							adresse.setText(gestionnaire.getAdress());
							naissance.setText(gestionnaire.getDateNaiss().toString());

						}
						panelEdition.setVisible(true);

					} catch (NumberFormatException ex) {
						//ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Oup ! Vérifier les saisies !");

					}catch (Exception e1) {
						// TODO Auto-generated catch block
						// e1.printStackTrace();
						//JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}

			}
		});

		suggestionPerso.setBounds(112, 106, 512, 27);
		suggestionPerso.setBackground(new Color(240, 240, 240));
		add(suggestionPerso);
		suggestionPerso.setEditable(true);

		JTextComponent editor = (JTextComponent) suggestionPerso.getEditor().getEditorComponent();
		editor.setDocument(new RecherchePersonnel(suggestionPerso.getModel(), suggestionPerso));

		JLabel rechercheLabel = new JLabel("Rechercher ");
		rechercheLabel.setBounds(31, 106, 81, 27);
		add(rechercheLabel);

		text1 = new JTextField();
		text1.setText("Editer les informations des personnels");
		text1.setBounds(128, 10, 526, 39);
		text1.setBackground(new Color(240, 240, 240));
		text1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		text1.setBorder(null);
		add(text1);
		text1.setColumns(10);

		setLayout(null);
		setBounds(0, 0, 692, 589);

		// Panel qui contient les champs à éditer
		panelEdition = new JPanel();
		panelEdition.setBounds(0, 126, 639, 417);
		add(panelEdition);
		panelEdition.setVisible(false);

		JLabel matriculeLabel = new JLabel("Matricule :");
		matriculeLabel.setBounds(31, 26, 84, 34);
		panelEdition.add(matriculeLabel);
		panelEdition.setLayout(null);

		matricule = new JTextField();
		matricule.setEditable(false);
		matricule.setBounds(112, 29, 193, 31);
		matricule.setBackground(new Color(255, 255, 255));
		panelEdition.add(matricule);
		matricule.setColumns(10);

		JLabel emailLabel = new JLabel("Email :");
		emailLabel.setBounds(350, 25, 52, 37);
		panelEdition.add(emailLabel);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(431, 26, 193, 34);
		email.setBackground(new Color(255, 255, 255));
		panelEdition.add(email);

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(31, 84, 61, 33);
		panelEdition.add(lblNom);

		nom = new JTextField();
		nom.setEditable(false);
		nom.setColumns(10);
		nom.setBounds(112, 84, 193, 33);
		nom.setBackground(new Color(255, 255, 255));
		panelEdition.add(nom);

		JLabel prenomLabel = new JLabel("Prenom(s) :");
		prenomLabel.setBounds(350, 82, 82, 36);
		panelEdition.add(prenomLabel);

		prenom = new JTextField();
		prenom.setEditable(false);
		prenom.setColumns(10);
		prenom.setBounds(431, 84, 193, 33);
		prenom.setBackground(new Color(255, 255, 255));
		panelEdition.add(prenom);

		JLabel telephoneLabel = new JLabel("telephone :");
		telephoneLabel.setBounds(31, 141, 72, 35);
		panelEdition.add(telephoneLabel);

		telephone = new JTextField();
		telephone.setColumns(10);
		telephone.setBounds(112, 140, 193, 36);
		telephone.setBackground(new Color(255, 255, 255));
		panelEdition.add(telephone);

		JLabel statutLabel = new JLabel("Statut :");
		statutLabel.setBounds(31, 206, 61, 36);
		panelEdition.add(statutLabel);

		JLabel addLabel = new JLabel("Adresse :");
		addLabel.setBounds(31, 281, 72, 24);
		panelEdition.add(addLabel);

		adresse = new JTextField();
		adresse.setColumns(10);
		adresse.setBounds(112, 278, 512, 31);
		adresse.setBackground(new Color(255, 255, 255));
		panelEdition.add(adresse);

		JLabel dateNaissanceLabel = new JLabel("Naissance :");
		dateNaissanceLabel.setBounds(350, 139, 72, 38);
		panelEdition.add(dateNaissanceLabel);

		naissance = new JTextField();
		naissance.setBounds(431, 141, 193, 35);
		naissance.setBackground(new Color(255, 255, 255));
		naissance.setEditable(false);
		panelEdition.add(naissance);

		enregisterModifs = new JButton("Enregistrer modifications");

		// Modification des données des personnelles
		enregisterModifs.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {

					String chaineDate = naissance.getText();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = dateFormat.parse(chaineDate);

					int telephoneSaisi = Integer.parseInt(telephone.getText());
					int matriculeSaisi = Integer.parseInt(matricule.getText());

					String statutChoisi = (String) statut.getSelectedItem();

					if (personnelChoisi().equals("agent")) {

						agent = new Agent(matriculeSaisi, email.getText(), telephoneSaisi, nom.getText(),
								prenom.getText(), date, adresse.getText(), statutChoisi, specialite.getText());
						
						if (JOptionPane.showConfirmDialog(null, "Veuillez confirmer la(les) modifications(s)", "Modification ?",
								JOptionPane.OK_CANCEL_OPTION) == 0)
							
						personnel.modifierAgent(agent);
					}
					if (personnelChoisi().equals("gestionnaire")) {

						int numService = Integer.parseInt(specialite.getText());

						gestionnaire = new Gestionnaire(matriculeSaisi, email.getText(), telephoneSaisi,
								nom.getText(), prenom.getText(), date, adresse.getText(), statutChoisi,
								numService);

						if (JOptionPane.showConfirmDialog(null, "Veuillez confirmer la(les) modifications(s)", "Modification ?",
								JOptionPane.OK_CANCEL_OPTION) == 0)
						personnel.modifierGestionnaire(gestionnaire);
					}

				} catch (NumberFormatException ex) {
					 //ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Oup ! Vérifier les saisies !");

				} catch (Exception e1) {
					// e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Oup ! " + e1.getMessage());
				}
			}
		});

		enregisterModifs.setBounds(410, 337, 214, 33);
		enregisterModifs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelEdition.add(enregisterModifs);

		try {

			if (personnel.getStatut().length != 0)
				statut = new JComboBox<String>(personnel.getStatut());
			else
				statut = new JComboBox<String>();
		} catch (Exception ignore) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}

		statut.setBounds(112, 208, 193, 34);
		statut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// statut.setEditable(false);
		// statut.setSelectedIndex(0);

		panelEdition.add(statut);

		JLabel specialiteLabel = new JLabel("Specialité :");
		specialiteLabel.setBounds(350, 206, 72, 36);
		panelEdition.add(specialiteLabel);

		specialite = new JTextField();
		specialite.setColumns(10);
		specialite.setBackground(Color.WHITE);
		specialite.setBounds(431, 209, 193, 33);
		panelEdition.add(specialite);

		// Ajout de boutons Radio pour choisir
		JRadioButton agentRadioButton = new JRadioButton("Agent");
		agentRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specialiteLabel.setText("Spécialité :");
				try {
					// Si le bouton agent est sélectionné, mettre à jour le contenu de la JComboBox
					// avec les agents
					suggestionPerso.setModel(
							new DefaultComboBoxModel<String>(personnel.listComboBoxAgent(personnel.getAgents())));
					suggestionPerso.setSelectedIndex(-1);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
		});

		agentRadioButton.setBounds(166, 55, 163, 30);
		agentRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		agentRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		agentRadioButton.setActionCommand("agent");
		add(agentRadioButton);

		JRadioButton gestionnaireRadiBtn = new JRadioButton("Gestionnaire");
		gestionnaireRadiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specialiteLabel.setText("Service :");

				try {
					// Si le bouton gestionnaire est sélectionné, mettre à jour le contenu de la
					// JComboBox avec les gestionnaires
					
					 if(personnel.getGestionnaires() != null)
					 {
					suggestionPerso.setModel(new DefaultComboBoxModel<String>(
							personnel.listComboBoxGestionnaire(personnel.getGestionnaires())));
					suggestionPerso.setSelectedIndex(-1);
					 }

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
		});
		gestionnaireRadiBtn.setBounds(350, 55, 163, 30);
		gestionnaireRadiBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gestionnaireRadiBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(gestionnaireRadiBtn);
		gestionnaireRadiBtn.setActionCommand("gestionnaire");

		group = new ButtonGroup(); // Grouper les boutons
		group.add(agentRadioButton);
		group.add(gestionnaireRadiBtn);

	}

	private String personnelChoisi() {
		String personnelChoisi = "";
		btnRadioSelectionne = group.getSelection();
		if (btnRadioSelectionne != null) {
			if (btnRadioSelectionne.getActionCommand().equals("agent"))
				// Le bouton agent est sélectionné

				personnelChoisi = "agent";
			else if (btnRadioSelectionne.getActionCommand().equals("gestionnaire"))
				// Le bouton gestionnaire est sélectionné
				personnelChoisi = "gestionnaire";
		}
		return personnelChoisi;
	}

	
}
