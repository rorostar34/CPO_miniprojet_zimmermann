/**
 * Romane Zimmermann
 * Projet Démineur
 */

package cpo_miniprojet_zimmermann;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FenetrePrincipale extends javax.swing.JFrame {

    private GrilleDeJeu grille; // Grille de jeu
    private int nbCoups;        // Nombre de coups

    public FenetrePrincipale(Partie partie) {
        this.grille = partie.getGrille();
        this.nbCoups = 0;

        initComponents(); // Appel après avoir défini grille
        initialiserGrilleGraphique();
        
        // Redimensionner la fenêtre pour qu'elle soit plus grande
        setSize(600, 600); // Ajuster la taille de la fenêtre
        jPanel1.setLayout(null); // Utilisation de null layout pour positionnement absolu
    }

    private void initialiserGrilleGraphique() {
        for (int i = 0; i < grille.getNbLignes(); i++) {
            for (int j = 0; j < grille.getNbColonnes(); j++) {
                Cellule_Graphique bouton = new Cellule_Graphique(i, j, grille.matriceCellules[i][j]);
                bouton.addActionListener(creerActionListener(bouton));

                // Augmenter la taille des cellules
                bouton.setBounds(j * 89, i * 75, 65, 70); // Cellule plus grande (70px de largeur et hauteur)
                jPanel1.add(bouton); // Ajouter chaque bouton à jPanel1
            }
        }
    }

    private ActionListener creerActionListener(Cellule_Graphique bouton) {
        return e -> {
            Cellule cellule = bouton.cellule_associe;
            if (!cellule.estDevoilee()) {
                cellule.revelerCellule();
                bouton.setText(cellule.getPresenceBombe() ? "💣" : String.valueOf(cellule.getNbBombesAdjacentes()));

                if (cellule.getPresenceBombe()) {
                    JOptionPane.showMessageDialog(this, "BOUM ! Game Over !");
                    System.exit(0);
                } else {
                    verifierEtatPartie();
                }
            }
        };
    }

    private void verifierEtatPartie() {
        if (grille.toutesCellulesRevelees()) {
            JOptionPane.showMessageDialog(this, "Félicitations, vous avez gagné !");
            System.exit(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel1.setLayout(new java.awt.GridLayout(4, 4));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 420, 360));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Partie partie = new Partie(5, 5, 4); // Exemple avec une grille 5x5 et 5 bombes
            new FenetrePrincipale(partie).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
