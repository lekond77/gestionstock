package vue.article;

import javax.swing.JPanel;
import javax.swing.JLabel;


import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import controleur.GererArticle;

import modeles.article.Article;
import modeles.article.Entrepot;
import modeles.article.Etagere;
import modeles.article.Stock;
import modeles.article.Zone;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class PanelAjouterArticle extends JPanel {

	//MArticleControleur mac = new MArticleControleur();
	GererArticle gArticle = new GererArticle();
	
	private JTextField textReferenceArticle;
	private JTextField textNomArticle;
	private JTextField textPrixArticle;
	private JTextField textStoks;
	private JTextArea textAreaDescriptionArticle;
	private JDateChooser dateCreation;
	private JComboBox comboBoxChoixEntrepot;
	private JComboBox comboBoxChoixEtagere;
	private JComboBox comboBoxChoixZone;
	

	public String codeChoixEmplacement;
	public String choix;
	private String codeChoixZone;
	private String codeChoixEtagere;
	
	/**
	 * Create the panel.
	 */
	public PanelAjouterArticle() {
		setBounds(0,0,668,543);
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(379, 98, 283, 254);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEmplacement = new JLabel("Emplacement");
		lblEmplacement.setBounds(6, 93, 84, 16);
		panel_1.add(lblEmplacement);
		
		JLabel lblStocks = new JLabel("Stocks");
		lblStocks.setBounds(6, 52, 84, 16);
		panel_1.add(lblStocks);
		
		textStoks = new JTextField();
		textStoks.setColumns(10);
		textStoks.setBounds(102, 47, 147, 26);
		panel_1.add(textStoks);

		
		// Créer et ajouter des objets au comboBox
	    comboBoxChoixEntrepot = new JComboBox();
		comboBoxChoixEtagere = new JComboBox();
		comboBoxChoixZone = new JComboBox();
	    
	    
	  
		  try {
			if(gArticle.exGetCodeEntrepots().length != 0) {
			   
				   comboBoxChoixEntrepot = new JComboBox<String>( gArticle.exGetCodeEntrepots());
				   comboBoxChoixEntrepot.setSelectedIndex(-1);
				   
			} else if(gArticle.exGetGetCodeZones(codeChoixEmplacement).length !=0) {
				   comboBoxChoixZone = new JComboBox<String>(gArticle.exGetGetCodeZones(codeChoixEmplacement)); 
				
				
			}else {
				   comboBoxChoixEntrepot = new JComboBox<String>();
					comboBoxChoixZone = new JComboBox<String>();
			   }
				
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
			
		    comboBoxChoixEntrepot.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		codeChoixEmplacement = (String) comboBoxChoixEntrepot.getSelectedItem();

					comboBoxChoixZone.removeAllItems();
		    		try {
						for(String zone : gArticle.exGetGetCodeZones(codeChoixEmplacement)) {
							comboBoxChoixZone.addItem(zone);
						 }
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		    	}
		    });

			comboBoxChoixZone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					codeChoixZone = (String) comboBoxChoixZone.getSelectedItem();

					comboBoxChoixEtagere.removeAllItems();
					
					try {
						for(String etagere : gArticle.exGetGetCodeEtagere(codeChoixZone)) {
							comboBoxChoixEtagere.addItem(etagere);
						 }
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			});

			comboBoxChoixEtagere.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					codeChoixEtagere = (String) comboBoxChoixEtagere.getSelectedItem();
				}
			});

		
	    comboBoxChoixEntrepot.setBounds(102, 89, 147, 27);
		panel_1.add(comboBoxChoixEntrepot);
		
		JLabel lblNewLabel = new JLabel("Zone");
		lblNewLabel.setBounds(6, 138, 84, 16);
		panel_1.add(lblNewLabel);
		
		comboBoxChoixZone.setBounds(102, 134, 147, 27);
		panel_1.add(comboBoxChoixZone);
		
		JLabel lblEtagere = new JLabel("Etagere");
		lblEtagere.setBounds(6, 179, 84, 16);
		panel_1.add(lblEtagere);
		
		comboBoxChoixEtagere.setBounds(102, 173, 147, 27);
		panel_1.add(comboBoxChoixEtagere);

		
		JLabel lblAjouterArticle = new JLabel("Ajouter Article");
		lblAjouterArticle.setHorizontalAlignment(SwingConstants.LEFT);
		lblAjouterArticle.setForeground(Color.BLACK);
		lblAjouterArticle.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblAjouterArticle.setBounds(74, 39, 273, 59);
		add(lblAjouterArticle);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					
					java.util.Date date =  dateCreation.getDate();
					java.sql.Date dateCreations=null;
					if(date != null) {
						dateCreations = new java.sql.Date(date.getTime());
					}
					gArticle.exAjouterArticle(new Article(textReferenceArticle.getText(), textNomArticle.getText(), dateCreations, Integer.parseInt(textPrixArticle.getText()),textAreaDescriptionArticle.getText()),
											  new Entrepot(codeChoixEmplacement),
											  new Zone(codeChoixZone),
											  new Etagere(codeChoixEtagere),
											  new Stock(Integer.parseInt(textStoks.getText())));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		btnAjouter.setBackground(new Color(0, 128, 128));
		btnAjouter.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnAjouter.setBounds(533, 463, 117, 29);
		add(btnAjouter);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(6, 98, 361, 394);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblReferenceArticle = new JLabel("Référence Article");
		lblReferenceArticle.setHorizontalAlignment(SwingConstants.LEFT);
		lblReferenceArticle.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblReferenceArticle.setBounds(16, 47, 136, 30);
		panel.add(lblReferenceArticle);
		
		JLabel lblNomProduit = new JLabel("Nom Article");
		lblNomProduit.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomProduit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNomProduit.setBounds(16, 89, 136, 30);
		panel.add(lblNomProduit);
		
		JLabel lblPrixProduit = new JLabel("Prix Article");
		lblPrixProduit.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrixProduit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPrixProduit.setBounds(16, 131, 136, 30);
		panel.add(lblPrixProduit);
		
		JLabel lblDateCration = new JLabel("Date création");
		lblDateCration.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateCration.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDateCration.setBounds(16, 173, 136, 30);
		panel.add(lblDateCration);
		
		JLabel lblDescriptionProduit = new JLabel("Description Article");
		lblDescriptionProduit.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescriptionProduit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDescriptionProduit.setBounds(16, 215, 131, 30);
		panel.add(lblDescriptionProduit);
		
		textReferenceArticle = new JTextField();
		textReferenceArticle.setColumns(10);
		textReferenceArticle.setBounds(150, 49, 191, 26);
		panel.add(textReferenceArticle);
		
		textNomArticle = new JTextField();
		textNomArticle.setColumns(10);
		textNomArticle.setBounds(150, 91, 191, 26);
		panel.add(textNomArticle);
		
		textPrixArticle = new JTextField();
		textPrixArticle.setColumns(10);
		textPrixArticle.setBounds(150, 133, 191, 26);
		panel.add(textPrixArticle);
		
		textAreaDescriptionArticle = new JTextArea();
		textAreaDescriptionArticle.setBounds(150, 222, 191, 132);
		panel.add(textAreaDescriptionArticle);
		
		dateCreation = new JDateChooser();
		dateCreation.setDateFormatString("d MMM y");
		dateCreation.setBounds(150, 173, 191, 26);
		panel.add(dateCreation);
		
		
	}
}
