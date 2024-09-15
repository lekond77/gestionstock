package vue.personnelle;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import controleur.GererPersonnel;
import modeles.personnel.Agent;
import modeles.personnel.Gestionnaire;
import vue.Acceuil;

public class ConsulterPersonnelPane extends JPanel {

	private JTextField text1;
	JComboBox<String> suggestionPerso, statut;
	JPanel panel;
	JPanel panelSupprimer;
	ButtonGroup group;
	Object[][] donnees;
	Object[][] donneesVide;

	private DefaultTableModel model = new DefaultTableModel();
	private JTable table = new JTable();
	private TableRowSorter<DefaultTableModel> filtre;

	private JTextField rechercheField;

	private Image img_recherche_personnel = new ImageIcon(
			ConsulterPersonnelPane.class.getResource("/icons/rechercher-p.jpg")).getImage()
			.getScaledInstance(48, 35, Image.SCALE_SMOOTH);
	private Image img_delete_user = new ImageIcon(Acceuil.class.getResource("/icons/remove_user.png")).getImage()
			.getScaledInstance(63, 38, Image.SCALE_SMOOTH);

	Agent agent = new Agent();
	Gestionnaire gestionnaire = new Gestionnaire();
	GererPersonnel personnel = new GererPersonnel();

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */

	public ConsulterPersonnelPane() {

		// Créer un filtre qui ne contient rien qui permttra d'afficher les éléments
		// de argent ou de gestionnaire lorsqu'une recherche a été faite

		filtre = new TableRowSorter<DefaultTableModel>();

		panelSupprimer = new JPanel();
		panelSupprimer.setBounds(10, 121, 682, 458);
		panelSupprimer.setLayout(null);

		add(panelSupprimer);

		String[] nomColonnes = { "Matricule", "Nom", "Prénom(s)", "Email", "Téléphone", "Statut", "Naissance",
				"Spécialité" };

		model.setColumnIdentifiers(nomColonnes);
		// model = new DefaultTableModel(donneesVide, nomColonnes);
		table.setModel(model);

		// Ajout de boutons Radio pour choisir
		JRadioButton agentRadioButton = new JRadioButton("Agent");
		agentRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				filtre.setRowFilter(null);
				TableColumnModel columnModel = table.getColumnModel();
				TableColumn column = columnModel.getColumn(7);
				column.setHeaderValue("Spécialité");
				try {

					List<Agent> agents = personnel.getAgents();
					// SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
					if (agents != null && agents.size() > 0) {

						donnees = new Object[agents.size()][8];

						for (int i = 0; i < agents.size(); i++) {
							Agent agent = agents.get(i);

							// LocalDate date =
							// LocalDate.parse(dateFormat.format((java.sql.Date)agent.getDateNaiss()));
							// int age = Period.between(LocalDate.now(), date).getYears();

							donnees[i][0] = agent.getMatricule();
							donnees[i][1] = agent.getNom().toUpperCase();
							donnees[i][2] = agent.getPrenom().toUpperCase();
							donnees[i][3] = agent.getEmail();
							donnees[i][4] = agent.getTelephone();
							donnees[i][5] = agent.getStatut();
							donnees[i][6] = agent.getDateNaiss();
							donnees[i][7] = agent.getSpecialite();
						}

						model = new DefaultTableModel(donnees, nomColonnes);
						table.setModel(model);
						table.getTableHeader().repaint();
					} else {
						model = new DefaultTableModel(donneesVide, nomColonnes);
						table.setModel(model);

					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				table.getTableHeader().repaint();
			}

		});

		agentRadioButton.setBounds(6, 81, 147, 30);
		agentRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		agentRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		agentRadioButton.setActionCommand("agent");
		add(agentRadioButton);

		JRadioButton gestionnaireRadiBtn = new JRadioButton("Gestionnaire");
		gestionnaireRadiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				filtre.setRowFilter(null);

				TableColumnModel columnModel = table.getColumnModel();
				TableColumn column = columnModel.getColumn(7);
				column.setHeaderValue("Service");

				try {
					// Si le bouton gestionnaire est sélectionné, mettre à jour le contenu de la
					// de la table avec les gestionnaires

					List<Gestionnaire> gestionnaires = personnel.getGestionnaires();

					if (gestionnaires != null && gestionnaires.size() > 0) {

						donnees = new Object[gestionnaires.size()][8];

						for (int i = 0; i < personnel.getGestionnaires().size(); i++) {
							Gestionnaire gestionnaire = gestionnaires.get(i);

							// LocalDate date = LocalDate.parse(dateFormat.format(agent.getDateNaiss()));

							// int age = Period.between(LocalDate.now(), date).getYears();
							donnees[i][0] = gestionnaire.getMatricule();
							donnees[i][1] = gestionnaire.getNom();
							donnees[i][2] = gestionnaire.getPrenom();
							donnees[i][3] = gestionnaire.getEmail();
							donnees[i][4] = gestionnaire.getTelephone();
							donnees[i][5] = gestionnaire.getStatut();
							donnees[i][6] = gestionnaire.getDateNaiss();
							donnees[i][7] = gestionnaire.getNumService();
						}

						model = new DefaultTableModel(donnees, nomColonnes);
						table.setModel(model);

					} else {
						model = new DefaultTableModel(donneesVide, nomColonnes);
						table.setModel(model);
					}
				} catch (Exception e1) {

					//e1.printStackTrace();
				}

				table.getTableHeader().repaint();
			}
		});

		gestionnaireRadiBtn.setBounds(162, 81, 163, 30);
		gestionnaireRadiBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gestionnaireRadiBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(gestionnaireRadiBtn);
		gestionnaireRadiBtn.setActionCommand("gestionnaire");

		group = new ButtonGroup(); // Grouper les boutons
		group.add(agentRadioButton);
		group.add(gestionnaireRadiBtn);

		text1 = new JTextField();
		text1.setText("Listes Personnels");
		text1.setBounds(190, 0, 260, 39);
		text1.setBackground(new Color(240, 240, 240));
		text1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		text1.setBorder(null);
		add(text1);
		text1.setColumns(10);

		// Création du tableau avec le modèle de données

		// Ajout du tableau à un JScrollPane pour permettre le défilement
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 594, 458);
		panelSupprimer.add(scrollPane);

		// Gestion de la suppression

		JButton buttonSupprimer = new JButton("");
		buttonSupprimer.setToolTipText("Supprrimer");
		buttonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int[] ligneSelectionne = table.getSelectedRows(); // Récupérer les index des lignes sélectionnées

				int indexMatricule = 0; // Index de la colonne matricule

				// On vérifie s'il n'y a pas d'erreur afin d'afficher le messsage de suppression
				// réussie
				boolean erreur = false;

				if (ligneSelectionne.length > 0) {

					if (JOptionPane.showConfirmDialog(null, "Veuillez confirmer la(les) suppression(s)", "Supprimer ?",
							JOptionPane.OK_CANCEL_OPTION) == 0) {

						for (int i = ligneSelectionne.length - 1; i >= 0; i--) {

							// Récupérer la valeur du matricule courante
							Object valeurRecupere = table.getValueAt(ligneSelectionne[i], indexMatricule);

							// Appel de méthode supprimerPersonnel du contorleur
							try {

								int matricule = Integer.parseInt(valeurRecupere.toString());

								// Supprimer la ligne de la base des données
								personnel.supprimerPersonnel(matricule);

								// Supprimer la ligne sélectionnée de la vue
								DefaultTableModel model = (DefaultTableModel) table.getModel();
								model.removeRow(ligneSelectionne[i]);

							} catch (NumberFormatException ex) {
								// ex.printStackTrace();
								JOptionPane.showMessageDialog(null, "Oup ! Vérifier le matricule!");
								erreur = true; // Il y a d'erreur

							} catch (Exception e1) {

								JOptionPane.showMessageDialog(null, e1.getMessage());
								// e1.printStackTrace();
								erreur = true; // Il y a d'erreur
							}
						}
						if (!erreur)
							JOptionPane.showMessageDialog(null, "Suppression(s) réussie(s) !");

					}

				}
			}
		});
		buttonSupprimer.setBounds(592, 0, 63, 38);
		buttonSupprimer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		buttonSupprimer.setIcon(new ImageIcon(img_delete_user));
		buttonSupprimer.setBackground(new Color(255, 255, 255));
		panelSupprimer.add(buttonSupprimer);

		setLayout(null);
		setBounds(0, 0, 692, 589);

	/*	JPanel panel_1 = new JPanel();
		panel_1.setBounds(331, 81, 271, 35);
		panel_1.setLayout(null);
		add(panel_1);

		rechercheField = new JTextField(20);
		rechercheField.setBounds(0, 0, 227, 35);
		panel_1.add(rechercheField);

		JButton rechercheButton = new JButton("");
		rechercheButton.setToolTipText("Rechercher");
		rechercheButton.setBounds(223, 0, 48, 34);
		rechercheButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.add(rechercheButton);
		rechercheButton.setIcon(new ImageIcon(img_recherche_personnel));
		rechercheButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Recupérer le texte du champ de recherche
				String recherchePersonnel = rechercheField.getText();

				// Ajout de filtre au tableau pour la recherche

				filtre = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(filtre);
				if (recherchePersonnel.length() != 0)

					filtre.setRowFilter(RowFilter.regexFilter("(?i)" + recherchePersonnel));

			}
		});*/

	}
	
}
