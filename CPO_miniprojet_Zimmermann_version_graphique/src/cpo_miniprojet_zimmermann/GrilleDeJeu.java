/**
 * Romane Zimmermann
 * Projet Démineur
 */

package cpo_miniprojet_zimmermann;
import cpo_miniprojet_zimmermann.Cellule;

public class GrilleDeJeu {
    Cellule[][] matriceCellules;
    private int nbLignes;
    private int nbColonnes;
    private int nbBombes;

    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;
        this.matriceCellules = new Cellule[nbLignes][nbColonnes];

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule();
            }
        }
    }

    public void placerBombesAleatoirement() {
        int bombesPlacees = 0;

        while (bombesPlacees < nbBombes) {
            int ligne = (int) (Math.random() * nbLignes);
            int colonne = (int) (Math.random() * nbColonnes);

            if (!matriceCellules[ligne][colonne].getPresenceBombe()) {
                matriceCellules[ligne][colonne].placerBombe();
                bombesPlacees++;
            }
        }
    }

    public void calculerBombesAdjacentes() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe()) {
                    int compteur = 0;

                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            int voisinLigne = i + x;
                            int voisinColonne = j + y;

                            if (voisinLigne >= 0 && voisinLigne < nbLignes &&
                                voisinColonne >= 0 && voisinColonne < nbColonnes &&
                                matriceCellules[voisinLigne][voisinColonne].getPresenceBombe()) {
                                compteur++;
                            }
                        }
                    }
                    matriceCellules[i][j].setNbBombesAdjacentes(compteur);
                }
            }
        }
    }
    public void reinitialiserBombes() {
    for (int i = 0; i < nbLignes; i++) {
        for (int j = 0; j < nbColonnes; j++) {
            matriceCellules[i][j].retirerBombe();
            matriceCellules[i][j].masquerCellule();
        }
    }
    placerBombesAleatoirement();
    calculerBombesAdjacentes();
}
    

    public void revelerCelluleParCoordonnees(int ligne, int colonne) {
    // Vérification des coordonnées valides
    if (ligne >= 0 && ligne < nbLignes && colonne >= 0 && colonne < nbColonnes) {
        Cellule cellule = matriceCellules[ligne][colonne];

        // Si la cellule est déjà dévoilée, on ne fait rien
        if (cellule.estDevoilee()) {
            return;
        }

        // Révéler la cellule
        cellule.revelerCellule();
        System.out.println("Cellule dévoilée aux coordonnées : (" + ligne + ", " + colonne + ")");

        // Si c'est une bombe, on arrête la propagation
        if (cellule.getPresenceBombe()) {
            return;
        }

        // Si la cellule contient un 0, révéler les cellules adjacentes
        if (cellule.getNbBombesAdjacentes() == 0) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x != 0 || y != 0) { // Éviter de réévaluer la cellule actuelle
                        revelerCelluleParCoordonnees(ligne + x, colonne + y);
                    }
                }
            }
        }
    }
}



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        for (int j = 0; j < nbColonnes; j++) {
            sb.append(j).append(" ");
        }
        sb.append("\n");

        for (int i = 0; i < nbLignes; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < nbColonnes; j++) {
                sb.append(matriceCellules[i][j].toString()).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public boolean toutesCellulesRevelees() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe() && !matriceCellules[i][j].estDevoilee()) {
                    return false;
                }
            }
        }
        return true;
    }
}
