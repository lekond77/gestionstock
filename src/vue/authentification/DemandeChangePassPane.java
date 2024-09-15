package vue.authentification;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;


import controleur.Authentification;
import modeles.Utilisateur;

import javax.swing.JTextArea;


public class DemandeChangePassPane extends JPanel {

	private JTextField email;
	private JButton demandeChangement, btnAnnuler;
	private JTextArea errorMessage;
	private JLabel emailField1;

	Authentification authentification;


	public DemandeChangePassPane() {

		setBounds(100, 100, 621, 480);
		setLayout(null);

		// Champ d'e-mail
		emailField1 = new JLabel("Entrer votre e-mail :");
		emailField1.setBounds(137, 152, 153, 31);
		add(emailField1);

		demandeChangement = new JButton("Envoyer");
		demandeChangement.setForeground(Color.WHITE);
		demandeChangement.setBackground(new Color(0, 128, 128));
		demandeChangement.setBounds(309, 264, 174, 43);
		demandeChangement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(demandeChangement);

		email = new JTextField();
		email.setBounds(137, 193, 349, 43);
		add(email);

		errorMessage = new JTextArea();
		errorMessage.setForeground(Color.RED);
		errorMessage.setEditable(false);
		errorMessage.setBorder(null);
		//errorMessage.setBackground(SystemColor.menu);
		errorMessage.setBackground(new Color(238, 238, 238));
		errorMessage.setBounds(137, 85, 349, 57);
		errorMessage.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		add(errorMessage);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnnuler.setBackground(new Color(255, 0, 0));
		btnAnnuler.setBounds(137, 264, 162, 43);
		add(btnAnnuler);

	}

	public JButton getEnvoyerEmail() {
		return demandeChangement;
	}
	
	public JButton getButtonAnnuler() {
		return btnAnnuler;
	}

	public JTextArea getErrorMessage() {
		return errorMessage;
	}

	public Utilisateur getEmailChangerPass() {

		return (new Utilisateur(email.getText()));
	}
}
