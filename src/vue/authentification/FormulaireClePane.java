package vue.authentification;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modeles.Utilisateur;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class FormulaireClePane extends JPanel {
	private JTextField cle;
	private JTextField email;
	private JButton envoyerCle, btnAnnuler;
	private JTextArea errorMessage;
	private JButton refreshBtn;

	/**
	 * Create the panel.
	 */
	public FormulaireClePane() {

		setLayout(null);
		refreshBtn = new JButton("Obtenir nouveau code");
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		refreshBtn.setBackground(new Color(0, 128, 128));
		refreshBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		refreshBtn.setBounds(333, 200, 167, 36);
		add(refreshBtn);

		cle = new JTextField();
		cle.setBounds(170, 201, 139, 36);
		add(cle);

		cle.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				checkPasswordLength();
			}

			public void removeUpdate(DocumentEvent e) {
				checkPasswordLength();
			}

			public void changedUpdate(DocumentEvent e) {
				checkPasswordLength();
			}

			private void checkPasswordLength() {
				if (cle.getText().length() == 6)
					envoyerCle.setEnabled(true);
				else
					envoyerCle.setEnabled(false);
			}
		});

		JLabel codeField1 = new JLabel("Entrer code");
		codeField1.setBounds(170, 172, 153, 31);
		add(codeField1);

		envoyerCle = new JButton("Envoyer");
		envoyerCle.setForeground(Color.WHITE);
		envoyerCle.setEnabled(false);
		envoyerCle.setBackground(new Color(0, 128, 128));
		envoyerCle.setBounds(333, 264, 167, 43);
		envoyerCle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(envoyerCle);

		email = new JTextField();
		email.setBounds(170, 119, 330, 43);
		add(email);

		JLabel emailField1 = new JLabel("Entrer votre e-mail :");
		emailField1.setBounds(170, 78, 153, 31);
		add(emailField1);

		errorMessage = new JTextArea();
		errorMessage.setForeground(Color.RED);
		errorMessage.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		errorMessage.setEditable(false);
		errorMessage.setBorder(null);
		//errorMessage.setBackground(SystemColor.menu);
		errorMessage.setBackground(new Color(238, 238, 238));
		errorMessage.setBounds(170, 24, 330, 60);
		add(errorMessage);

		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setBackground(new Color(255, 0, 0));
		btnAnnuler.setBounds(173, 264, 136, 43);
		add(btnAnnuler);
		btnAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

	}

	public JButton getEnvoyerCle() {
		return envoyerCle;
	}

	public JTextArea getErrorMessage() {
		return errorMessage;
	}

	public JTextField getCle() {
		return cle;
	}

	public Utilisateur getCleChangerPass() {

		return (new Utilisateur(email.getText(), Integer.parseInt(cle.getText())));

	}

	public JButton getRefreshBtn() {
		return refreshBtn;
	}
	
	public JButton getButtonAnnuler() {
		return btnAnnuler;
	}

	public JTextField getEmailField() {
		return email;
	}

}
