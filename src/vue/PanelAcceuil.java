package vue;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class PanelAcceuil extends JPanel {

	private Image img_page_acceuil = new ImageIcon(Acceuil.class.getResource("/icons/image_acceuil.png")).getImage().getScaledInstance(450, 264, Image.SCALE_SMOOTH);
	/**
	 * Create the panel.
	 */
	public PanelAcceuil() {
		setSize(668,543);
		setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(51, 102, 102));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel.setIcon(new ImageIcon(img_page_acceuil));
		lblNewLabel.setBounds(86, 140, 481, 249);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenue sur GS");
		lblNewLabel_1.setBackground(new Color(0, 0, 255));
		lblNewLabel_1.setForeground(new Color(10, 10, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 50));

		lblNewLabel_1.setBounds(151, 29, 400, 84);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Application de gestion de stockage des produits");
		lblNewLabel_1_1.setBackground(new Color(0, 0, 255));
		lblNewLabel_1_1.setForeground(new Color(10, 10, 255));
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(47, 412, 593, 84);
		add(lblNewLabel_1_1);

	}
}
