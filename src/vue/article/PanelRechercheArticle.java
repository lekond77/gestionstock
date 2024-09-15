package vue.article;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controleur.GererArticle;
import modeles.article.Article;
import modeles.article.Entrepot;
import modeles.article.Stock;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class PanelRechercheArticle extends JPanel {
	private JTextField textReferenceChercher;

	//MArticleControleur mac = new MArticleControleur();
	GererArticle gArticle = new GererArticle();
	
	Entrepot entrepot = new Entrepot();
	private JTextField textAffNomArticle;
	private JTextField textAffPrix;
	private JTextField textStock;
	private JTextArea textAreaAffDescription;
	private JDateChooser dateChooser;
	private JComboBox cBEmplacement;
	private JComboBox cBZone;
	private JComboBox cBEtagere;
	private int idrec;
	JButton btnModifierArticle;
	
	
	

	
	private String mcode;
	private String zcode;
	private String ecode;


	/**
	 * Create the panel.
	 */
	public PanelRechercheArticle() {
		
		
		setSize(668,543);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(6, 6, 656, 157);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblrefRechercheArticle = new JLabel("Reference de l'article");
		lblrefRechercheArticle.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblrefRechercheArticle.setBounds(16, 104, 138, 29);
		panel.add(lblrefRechercheArticle);
		
		 btnModifierArticle = new JButton("Modifier");
		
		 JButton btnRechercherArticle = new JButton("Rechercher");
		
		// Boutton rechercher un produit
		btnRechercherArticle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Article article = new Article();
			Stock stock = new Stock();
			try {
				article = gArticle.exRechercherArticle(textReferenceChercher.getText());
				stock = gArticle.exRechercherStock(textReferenceChercher.getText());
				
				textAffNomArticle.setText(article.getNomArticle());
				dateChooser.setDate(article.getDateCreation());
				textStock.setText(stock.getQuantiteStock()+"");
				textAffPrix.setText(article.getPrixArticle()+"");
					
				for(Entrepot ent : gArticle.exAfficherEntrepots()) {
					cBEmplacement.addItem(ent);
					mcode = ent.getcodeEntrepot();
					}
				
				for(Stock zone : gArticle.exInfoArticles()) {
					zcode = zone.getZone();
					cBZone.addItem(zcode);;
					}
				
				for(Stock etage : gArticle.exInfoArticles()) {
					ecode = etage.getEtagere();
					cBEtagere.addItem(ecode);
					}
				textAreaAffDescription.setText(article.getDescriptionArticle());
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
					
			// Boutton modification d'un produit
			btnModifierArticle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					java.util.Date date =  dateChooser.getDate();
					java.sql.Date dateCreations=null;
					if(date != null) {
						dateCreations = new java.sql.Date(date.getTime());
					}
					
					try {
						gArticle.exModifierArticle(new Article(textAffNomArticle.getText(), dateCreations, Integer.parseInt(textAffPrix.getText()), textAreaAffDescription.getText(), textReferenceChercher.getText()),
													/*new Stock(Integer.parseInt(textStock.getText()),mcode,zcode,ecode,textReferenceChercher.getText()*/
													new Stock(Integer.parseInt(textStock.getText()),textReferenceChercher.getText())
														);
					} catch (Exception e1) {
						e1.printStackTrace();
					} }
			});
				
			}
			
		});

		
		btnRechercherArticle.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnRechercherArticle.setBounds(519, 104, 131, 31);
		panel.add(btnRechercherArticle);
		
		JLabel lblConsulterLemplacement = new JLabel("Rechercher un article");
		lblConsulterLemplacement.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulterLemplacement.setForeground(Color.BLACK);
		lblConsulterLemplacement.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblConsulterLemplacement.setBounds(0, 6, 656, 59);
		panel.add(lblConsulterLemplacement);
		
		textReferenceChercher = new JTextField();
		textReferenceChercher.setBounds(153, 104, 360, 29);
		panel.add(textReferenceChercher);
		textReferenceChercher.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(6, 175, 656, 362);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Informations article");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(6, 6, 644, 25);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nom article");
		lblNewLabel_2.setBounds(20, 79, 92, 16);
		panel_1.add(lblNewLabel_2);
		
		textAffNomArticle = new JTextField();
		textAffNomArticle.setBounds(147, 74, 130, 26);
		panel_1.add(textAffNomArticle);
		textAffNomArticle.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Prix article");
		lblNewLabel_3.setBounds(20, 121, 83, 16);
		panel_1.add(lblNewLabel_3);
		
		textAffPrix = new JTextField();
		textAffPrix.setBounds(147, 116, 130, 26);
		panel_1.add(textAffPrix);
		textAffPrix.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Date creation");
		lblNewLabel_4.setBounds(20, 167, 92, 16);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Description");
		lblNewLabel_5.setBounds(20, 213, 83, 16);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Emplacement");
		lblNewLabel_6.setBounds(337, 121, 92, 16);
		panel_1.add(lblNewLabel_6);
		
		textAreaAffDescription = new JTextArea();
		textAreaAffDescription.setBounds(147, 213, 170, 92);
		panel_1.add(textAreaAffDescription);
		

		btnModifierArticle.setBounds(519, 314, 117, 29);
		panel_1.add(btnModifierArticle);
		
		JLabel lblNewLabel_7 = new JLabel("Stock");
		lblNewLabel_7.setBounds(337, 79, 92, 16);
		panel_1.add(lblNewLabel_7);
		
		textStock = new JTextField();
		textStock.setBounds(458, 74, 130, 26);
		panel_1.add(textStock);
		textStock.setColumns(10);
		
		cBEmplacement = new JComboBox();
		cBEmplacement.setBounds(458, 117, 136, 27);
		panel_1.add(cBEmplacement);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(147, 157, 130, 26);
		panel_1.add(dateChooser);
		
		JLabel lblNewLabel_6_1 = new JLabel("Zone");
		lblNewLabel_6_1.setBounds(337, 160, 92, 16);
		panel_1.add(lblNewLabel_6_1);
		
		cBZone = new JComboBox();
		cBZone.setBounds(458, 156, 136, 27);
		panel_1.add(cBZone);
		
		cBEtagere = new JComboBox();
		cBEtagere.setBounds(458, 195, 136, 27);
		panel_1.add(cBEtagere);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("Etagere");
		lblNewLabel_6_1_1.setBounds(337, 199, 92, 16);
		panel_1.add(lblNewLabel_6_1_1);

	}
	
	public JButton getBtnModifier() {
		return btnModifierArticle;
	}
	
}
