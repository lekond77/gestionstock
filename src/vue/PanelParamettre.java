package vue;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;

public class PanelParamettre extends JPanel {
	private Image maintenance = new ImageIcon(Acceuil.class.getResource("/icons/maintenance.png")).getImage().getScaledInstance(406, 292, Image.SCALE_SMOOTH);


	/**
	 * Create the panel.
	 */
	public PanelParamettre() {
		setBounds(0,0,668,543); 
		setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(maintenance));
		lblNewLabel_5.setBounds(348, 331, 119, 114);
		add(lblNewLabel_5);

	}
}
