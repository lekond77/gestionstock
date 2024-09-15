package vue.personnelle;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.*;

import controleur.GererPersonnel;
import modeles.personnel.Agent;
import modeles.personnel.GestionPersonnelle;
import modeles.personnel.Gestionnaire;
import vue.Acceuil;
import javax.swing.JRadioButton;


public class AjoutPersonnelPane extends JPanel {

	private JTextField matricule, email, nom;
	private JTextField prenom;
	private JTextField telephone;
	private JTextField adresse;
	private JTextField specialite;
	private JDateChooser naissance;
	JComboBox statut;

	private Agent agent;
	Gestionnaire gestionnaire;
	JButton ajouter;
	private ButtonModel btnRadioSelectionne;
	ButtonGroup group;
	GestionPersonnelle gPersonnel = new GestionPersonnelle();
	GererPersonnel personnel = new GererPersonnel();
	Acceuil acceuil;

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public AjoutPersonnelPane() {

		// Panel d'affichage des informations sur le personnell

		setLayout(null);
		setBounds(0, 0, 692, 589);

		JLabel matriculeLabel = new JLabel("Matricule :");
		matriculeLabel.setBounds(31, 130, 84, 34);
		add(matriculeLabel);

		matricule = new JTextField();
		matricule.setBounds(112, 133, 193, 31);
		matricule.setBackground(new Color(255, 255, 255));
		add(matricule);
		matricule.setColumns(10);

		JLabel emailLabel = new JLabel("Email :");
		emailLabel.setBounds(353, 129, 52, 37);
		add(emailLabel);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(431, 130, 193, 34);
		email.setBackground(new Color(255, 255, 255));
		add(email);

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(31, 188, 61, 33);
		add(lblNom);

		nom = new JTextField();
		nom.setColumns(10);
		nom.setBounds(112, 188, 193, 33);
		nom.setBackground(new Color(255, 255, 255));
		add(nom);

		JLabel prenomLabel = new JLabel("Prenom(s) :");
		prenomLabel.setBounds(350, 186, 82, 36);
		add(prenomLabel);

		prenom = new JTextField();
		prenom.setColumns(10);
		prenom.setBounds(431, 188, 193, 33);
		prenom.setBackground(new Color(255, 255, 255));
		add(prenom);

		JLabel telephoneLabel = new JLabel("telephone :");
		telephoneLabel.setBounds(31, 245, 72, 35);
		add(telephoneLabel);

		telephone = new JTextField();
		telephone.setColumns(10);
		telephone.setBounds(112, 244, 193, 36);
		telephone.setBackground(new Color(255, 255, 255));
		add(telephone);

		JLabel statutLabel = new JLabel("Statut :");
		statutLabel.setBounds(31, 310, 61, 36);
		add(statutLabel);

		JLabel addLabel = new JLabel("Adresse :");
		addLabel.setBounds(31, 374, 72, 24);
		add(addLabel);

		adresse = new JTextField();
		adresse.setColumns(10);
		adresse.setBounds(112, 371, 512, 31);
		adresse.setBackground(new Color(255, 255, 255));
		add(adresse);

		JLabel dateNaissanceLabel = new JLabel("Naissance :");
		dateNaissanceLabel.setBounds(353, 243, 72, 38);
		add(dateNaissanceLabel);

		naissance = new JDateChooser();
		naissance.setDateFormatString("yyyy-MM-dd");

		naissance.setBounds(431, 245, 193, 35);
		naissance.setBackground(new Color(255, 255, 255));
		naissance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(naissance);

		ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int telephoneSaisi = Integer.parseInt(telephone.getText());
					int matriculeSaisi = Integer.parseInt(matricule.getText());

					String statutChoisi = (String) statut.getSelectedItem();

					if (personnelChoisi().equals("agent")) {

						agent = new Agent(matriculeSaisi, email.getText(), telephoneSaisi, nom.getText(),
								prenom.getText(), naissance.getDate(), adresse.getText(), statutChoisi,
								specialite.getText());

						personnel.executeAjoutAgent(agent);
					}
					if (personnelChoisi().equals("gestionnaire")) {
						int numService = Integer.parseInt(specialite.getText());

						gestionnaire = new Gestionnaire(matriculeSaisi, email.getText(), telephoneSaisi,
								nom.getText(), prenom.getText(), naissance.getDate(), adresse.getText(), statutChoisi,
								numService);

						personnel.executeAjoutGestionnaire(gestionnaire);
					}

				} catch (NumberFormatException ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Oup ! Vérifier les saisies !");

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				(new ModifierPersonnelPane()).revalidate();
			}
		});
		ajouter.setBounds(239, 441, 193, 33);
		ajouter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(ajouter);

		// statut = new JComboBox((new AjoutStatut()).statuts());
		try {
			if (personnel.getStatut().length != 0)
				statut = new JComboBox<String>(personnel.getStatut());
			else
				statut = new JComboBox<String>();
		} catch (Exception ignore) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		statut.setEditable(false);
		statut.setSelectedIndex(0);
		statut.setBounds(112, 312, 193, 34);
		statut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(statut);

		JLabel specialiteLabel = new JLabel("Specialité :");
		specialiteLabel.setBounds(353, 310, 72, 36);
		add(specialiteLabel);

		specialite = new JTextField();
		specialite.setColumns(10);
		specialite.setBackground(Color.WHITE);
		specialite.setBounds(431, 313, 193, 33);
		add(specialite);

		JLabel lblNewLabel = new JLabel("Ajouter Personnel");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(191, 10, 266, 39);
		add(lblNewLabel);

		// Ajout de boutons Radio pour choisir
		JRadioButton agentRadioButton = new JRadioButton("Agent");
		agentRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specialiteLabel.setText("Spécialité :");
			}
		});
		agentRadioButton.setSelected(true);
		agentRadioButton.setBounds(172, 62, 163, 30);
		agentRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		agentRadioButton.setActionCommand("agent");
		agentRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(agentRadioButton);

		JRadioButton gestionnaireRadiBtn = new JRadioButton("Gestionnaire");
		gestionnaireRadiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specialiteLabel.setText("Service :");
			}
		});
		gestionnaireRadiBtn.setBounds(345, 62, 163, 30);
		gestionnaireRadiBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		gestionnaireRadiBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(gestionnaireRadiBtn);
		gestionnaireRadiBtn.setActionCommand("gestionnaire");

		group = new ButtonGroup(); // Grouper les boutons
		group.add(agentRadioButton);
		group.add(gestionnaireRadiBtn);
		

	}

	// Choix du gestionnaire ou agent
	public String personnelChoisi() {
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
