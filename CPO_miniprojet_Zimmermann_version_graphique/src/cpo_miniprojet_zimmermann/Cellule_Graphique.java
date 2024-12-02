/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cellule_Graphique extends JButton {
    int x;
    int y;
    Cellule cellule_associe;

    public Cellule_Graphique(int x, int y, Cellule cellule_associe) {
        this.x = x;
        this.y = y;
        this.cellule_associe = cellule_associe;

        // Ajouter un ActionListener pour gérer le clic sur ce bouton
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Révéler la cellule associée
                if (!cellule_associe.estDevoilee()) {
                    cellule_associe.revelerCellule();
                    mettreAJourAffichage();
                }
            }
        });
    }

    // Méthode pour mettre à jour l'affichage du bouton
    public void mettreAJourAffichage() {
        if (cellule_associe.estDevoilee()) {
            if (cellule_associe.getPresenceBombe()) {
                setText("B"); // Afficher une bombe
            } else if (cellule_associe.getNbBombesAdjacentes() > 0) {
                setText(String.valueOf(cellule_associe.getNbBombesAdjacentes())); // Afficher le nombre de bombes adjacentes
            } else {
                setText(""); // Cellule vide
            }
            setEnabled(false); // Désactiver le bouton une fois dévoilé
        }
    }
}
