package vue.authentification;

import java.awt.Color;
import java.awt.Cursor;

import java.awt.Font;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;


public class PassChangePane extends JFrame{

private JPanel contentPane;
private JTextField txtVotreMotDe;
private JButton backBtn ;


	public PassChangePane() {
		setBounds(100, 100, 545, 359);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtVotreMotDe = new JTextField();
		txtVotreMotDe.setText("Votre mot de passe a été changé avec succés !");
		txtVotreMotDe.setBounds(22, 66, 485, 55);
		contentPane.add(txtVotreMotDe);
		txtVotreMotDe.setColumns(10);
		txtVotreMotDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		txtVotreMotDe.setBorder(null);
		
		backBtn = new JButton("Retour à la page de connexion");
		backBtn.setBounds(141, 188, 263, 39);
		backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		backBtn.setBackground(new Color(0, 128, 128));
		//backBtn.setForeground(new Color(255, 255, 255));
		contentPane.add(backBtn);
	
		
	}


	public JButton getBackBtn() {
		return backBtn;
	}
	
	
}
