package vue.personnelle;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class RecherchePersonnel extends PlainDocument {
	ComboBoxModel modelComBox;
	JComboBox suggestionPerso;

	// le ComboBoxModel à utiliser
	public RecherchePersonnel(ComboBoxModel modelComBox, JComboBox suggestionPerso) {
		this.modelComBox = modelComBox;
		this.suggestionPerso = suggestionPerso;
	}

	/*
	 * Méthode qui sera appelée lorsqu'un utilisateur insère du texte dans le champ
	 * de recherche la méthode insertString est de PlainDocument : offs : position
	 * du curseur, str: la chaine à insérer a : infos sur l'attribut
	 */

	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

		// Insère la chaîne de caractères dans le document
		super.insertString(offs, str, a);
		// Récupère la chaîne de caractères résultante
		String content = getText(0, getLength());
		// Recherche un élément correspondant

		// String content = getText(0, getLength()).toLowerCase();

		// suggestionPerso.removeAllItems();

		/*
		 * for (String item : suggestionPerso) { if
		 * (item.toLowerCase().startsWith(content)) { suggestionPerso.addItem(item); } }
		 */

		String item = lookupItem(content);

		// Si la liste de suggestions n'est pas vide, affiche la liste de suggestions
		if (suggestionPerso.getItemCount() > 0) {
			suggestionPerso.showPopup();
		} else {
			suggestionPerso.hidePopup();
		}
		// Sélectionne l'élément correspondant (ou le déselectionne si item est null)
		modelComBox.setSelectedItem(item);
	}

	// Méthode pour rechercher un élément correspondant dans le ComboBoxModel
	private String lookupItem(String pattern) {
		// Itère sur tous les éléments du ComboBoxModel
		for (int i = 0, n = modelComBox.getSize(); i < n; i++) {
			String currentItem = (String) modelComBox.getElementAt(i);
			// L'élément courant commence-t-il par la chaîne de caractères recherchée ?
			if (currentItem.toString().toLowerCase().contains(pattern.toLowerCase())) {
				return (String) currentItem;
			}
		}
		// Aucun élément ne correspond à la chaîne de caractères recherchée => retourne
		// null
		return null;
	}

}