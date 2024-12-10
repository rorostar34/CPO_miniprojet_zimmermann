/**
 * Romane Zimmermann
 * Projet Démineur
 */
package cpo_miniprojet_zimmermann;

import javax.swing.JButton;
public class Cellule_Graphique extends JButton {
    int x;
    int y;
    Cellule cellule_associe;

    public Cellule_Graphique(int x, int y, Cellule cellule_associe) {
        this.x = x;
        this.y = y;
        this.cellule_associe = cellule_associe;
    }
}  