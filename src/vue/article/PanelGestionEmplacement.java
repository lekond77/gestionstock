package vue.article;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Image;

import javax.swing.JTabbedPane;


import vue.Acceuil;

import javax.swing.ImageIcon;

public class PanelGestionEmplacement extends JPanel {
	private Image maintenance = new ImageIcon(Acceuil.class.getResource("/icons/maintenance.png")).getImage().getScaledInstance(406, 292, Image.SCALE_SMOOTH);

	

	/**
	 * Create the panel.
	 */
	public PanelGestionEmplacement() {
		setSize(668,543);
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 656, 531);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Consulter", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(maintenance));
		lblNewLabel_5.setBounds(119, 85, 406, 292);
		panel.add(lblNewLabel_5);

		
		JPanel ajoutEmplacement = new JPanel();
		tabbedPane.addTab("Ajouter", null, ajoutEmplacement, null);
		ajoutEmplacement.setLayout(null);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setIcon(new ImageIcon(maintenance));
		lblNewLabel_5_1.setBounds(98, 92, 406, 292);
		ajoutEmplacement.add(lblNewLabel_5_1);
		
		JPanel modifierEmplacement = new JPanel();
		tabbedPane.addTab("Modifier", null, modifierEmplacement, null);
		modifierEmplacement.setLayout(null);
		
		JLabel lblNewLabel_5_2 = new JLabel("");
		lblNewLabel_5_2.setIcon(new ImageIcon(maintenance));
		lblNewLabel_5_2.setBounds(97, 87, 406, 292);
		modifierEmplacement.add(lblNewLabel_5_2);
		
		JPanel listEmplacement = new JPanel();
		tabbedPane.addTab("Liste", null, listEmplacement, null);
		listEmplacement.setLayout(null);
		
		JLabel lblNewLabel_5_3 = new JLabel("");
		lblNewLabel_5_3.setIcon(new ImageIcon(maintenance));
		lblNewLabel_5_3.setBounds(99, 89, 406, 292);
		listEmplacement.add(lblNewLabel_5_3);

	}
}
