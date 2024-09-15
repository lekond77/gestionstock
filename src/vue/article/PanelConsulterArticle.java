package vue.article;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controleur.GererArticle;

import modeles.article.Article;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelConsulterArticle extends JPanel {
	private JTable table;
	DefaultTableModel model;
	Article Article = new Article();

	GererArticle gArticle = new GererArticle();

	/**
	 * Create the panel.
	 */
	public PanelConsulterArticle() {
		setSize(668,543);
		setLayout(null);
		
		JLabel lblAListArticle = new JLabel("List des Article");
		lblAListArticle.setHorizontalAlignment(SwingConstants.LEFT);
		lblAListArticle.setForeground(Color.BLACK);
		lblAListArticle.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblAListArticle.setBounds(31, 6, 273, 59);
		add(lblAListArticle);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(6, 52, 564, 485);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 552, 473);
		panel.add(scrollPane);
		
		table = new JTable();
		
		model = new DefaultTableModel();
		
		Object[] columnsName = new Object[5];
		
		columnsName[0] = "Reference";
        columnsName[1] = "Nom";
        columnsName[2] = "Prix";
        //columnsName[3] = "Stock";
        columnsName[3] = "Date";
        columnsName[4] = "Entrepot";
        
        model.setColumnIdentifiers(columnsName);
        
        Object[] rowData = new Object[5];
        int j;
        
        try {
			for(int i = 0; i < gArticle.exAfficherArticle().size(); i++){
			    
			    rowData[0] = gArticle.exAfficherArticle().get(i).getReferenceArticle();
			    rowData[1] = gArticle.exAfficherArticle().get(i).getNomArticle();
			    rowData[2] = gArticle.exAfficherArticle().get(i).getPrixArticle();
			    //rowData[3] = mac.afficherArticle().get(i).getStock();
			    rowData[3] = gArticle.exAfficherArticle().get(i).getDateCreation();
			    rowData[4] = gArticle.exAfficherEntrepots().get(i).getLibelleEntrepot();
			    
			    model.addRow(rowData);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        table.setModel(model);
        table.getTableHeader().repaint();
        
        scrollPane.setViewportView(table);
        
        JButton btnModifier = new JButton("Actualiser");
        btnModifier.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		table.updateUI();	
        		
        	}
        });
        btnModifier.setBounds(568, 52, 94, 29);
        add(btnModifier);
        
        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i = table.getSelectedRow();
        		try {
					gArticle.exSupprimerArticle(gArticle.exAfficherArticle().get(i).getIdArticle());
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.removeRow(i); 
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}table.setModel(model);
        		table.getTableHeader().repaint();
        		
        	}
        });
        btnSupprimer.setBounds(568, 87, 94, 29);
        add(btnSupprimer);
        

	}
}
