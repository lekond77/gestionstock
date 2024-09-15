package vue.authentification;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modeles.Utilisateur;

public class FormulaireMiseAJourPane extends JPanel {

	private JTextField email;
	private JPasswordField password;
	private JButton changePass;
	private JTextField errorMessage;
	private JPasswordField passwordBis;


	public FormulaireMiseAJourPane() {

		
		setBounds(100, 100, 587, 446);
		setLayout(null);

		// Champ d'identifiant
		JLabel idField = new JLabel("Email ");
		idField.setBounds(132, 111, 74, 29);

		email = new JTextField();
		email.setBounds(132, 138, 319, 40);
		email.setColumns(10);

		// Champs de mot de passe
		JLabel passField = new JLabel("Nouveau mot de passe");
		passField.setBounds(132, 188, 186, 26);

		password = new JPasswordField();
		password.setToolTipText("");
		password.setBounds(132, 214, 319, 40);

		// Boutton de connexion
		changePass = new JButton("Envoyer");

		changePass.setBounds(207, 338, 162, 40);
		changePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		changePass.setForeground(new Color(10, 50, 10));
		changePass.setBackground(new Color(0, 128, 128));

		// Ajout des composants crées

		add(idField);
		add(email);
		add(passField);
		add(changePass);
		add(password);

		errorMessage = new JTextField();
		errorMessage.setForeground(new Color(255, 0, 0));
		errorMessage.setBackground(new Color(238, 238, 238));
		errorMessage.setEditable(false);
		errorMessage.setBorder(null);
		errorMessage.setBounds(132, 58, 317, 48);
		add(errorMessage);

		passwordBis = new JPasswordField();
		passwordBis.setToolTipText("");
		passwordBis.setBounds(132, 288, 319, 40);
		add(passwordBis);

		JLabel lblConfirmerMotDe = new JLabel("Confirmer mot de passe");
		lblConfirmerMotDe.setBounds(132, 263, 186, 26);
		add(lblConfirmerMotDe);

	}

	public JButton getChangePass() {
		return changePass;
	}
	

	public JTextField getEmail() {
		return email;
	}

	public JTextField getErrorMessage() {
		return errorMessage;
	}

	public Utilisateur getMotDePasseChanger() {

		Utilisateur utilisateur = new Utilisateur(email.getText(), String.valueOf(password.getPassword()),
				String.valueOf(passwordBis.getPassword()));

		return utilisateur;
	}

}