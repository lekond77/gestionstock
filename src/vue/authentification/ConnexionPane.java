package vue.authentification;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import modeles.Utilisateur;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

public class ConnexionPane extends JFrame {

	private JPanel contentPane;
	private JTextField identifiant;
	private JPasswordField password;
	private JButton login;
	private JTextField errorMessage;
	private JLabel passOubliee;
	private JPanel panelMain, loginPanel;
	Utilisateur user;
	Utilisateur utilisateur;

	private static final long serialVersionUID = 1L;

	public ConnexionPane() {

		setBounds(100, 100, 885, 593);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 203, 593);
		panelMenu.setBackground(new Color(51, 102, 102));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		JPanel panelMenuHeader = new JPanel();
		panelMenuHeader.setBackground(new Color(51, 102, 102));
		panelMenuHeader.setBounds(201, 0, 684, 43);
		contentPane.add(panelMenuHeader);
		panelMenuHeader.setLayout(null);

		JLabel lblExit = new JLabel("X");
		lblExit.setBounds(640, 6, 38, 31);
		panelMenuHeader.add(lblExit);
		lblExit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);

		panelMain = new JPanel(new BorderLayout());

		panelMain.setBounds(211, 47, 668, 540);
		contentPane.add(panelMain);
		panelMain.setLayout(null);

		lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblExit.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent argO) {
				if (JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir quitter l'application", "Confirmer",
						JOptionPane.YES_NO_OPTION) == 0) {
					ConnexionPane.this.dispose();
				}
				;
			}

			@Override
			public void mouseEntered(MouseEvent Arg0) {
				lblExit.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent Arg0) {
				lblExit.setForeground(Color.gray);
			}
		});

		//// *****************/////
		JLabel idField = new JLabel("Identifiant");
		idField.setBounds(109, 100, 74, 29);

		identifiant = new JTextField();
		identifiant.setBounds(109, 134, 319, 29);
		identifiant.setColumns(10);

		// Champs de mot de passe
		JLabel passField = new JLabel("Mot de passe");
		passField.setBounds(109, 173, 97, 26);

		password = new JPasswordField();
		password.setToolTipText("");
		password.setBounds(109, 209, 319, 29);

		// Boutton de connexion
		login = new JButton("Connexion");

		login.setBounds(173, 301, 162, 35);
		login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// login.setForeground(new Color(255, 255, 255));
		login.setBackground(new Color(0, 128, 128));
		login.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		// Champ de gestion de mot de passe oublie

		passOubliee = new JLabel("<html><u>Mot de passe oublié ?</u></html>");
		passOubliee.setBounds(292, 242, 136, 26);
		passOubliee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		passOubliee.setForeground(Color.BLUE.darker());

		errorMessage = new JTextField();
		errorMessage.setForeground(new Color(255, 0, 0));
		errorMessage.setBackground(new Color(238, 238, 238));
		// errorMessage.setBackground(new Color(192, 192, 192));
		errorMessage.setEditable(false);
		errorMessage.setBorder(null);
		errorMessage.setBounds(109, 35, 319, 54);

		// authentification pane

		loginPanel = new JPanel();
		loginPanel.setBounds(28, 10, 617, 520);
		loginPanel.setLayout(null);
		loginPanel.setBorder(null);

		// Ajout des composants crées

		panelMain.add(loginPanel);

		loginPanel.add(idField);
		loginPanel.add(identifiant);
		loginPanel.add(passField);
		loginPanel.add(login);
		loginPanel.add(password);
		loginPanel.add(passOubliee);
		loginPanel.add(errorMessage);

	}

	public JPasswordField getPassword() {
		return password;
	}

	public JTextField getErrorMessage() {
		return errorMessage;
	}

	public JButton getLogin() {
		return login;
	}

	public Utilisateur utilisateurDemandeConnexion() {

		utilisateur = new Utilisateur(identifiant.getText(), String.valueOf(password.getPassword()));

		return utilisateur;
	}

	// Renvoie le texte d'évènement
	public JLabel eventToUpdatePass() {
		return passOubliee;
	}

	public JPanel getMainPanel() {
		return panelMain;
	}

	public JPanel getLoginPanel() {
		return loginPanel;
	}

}
