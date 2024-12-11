/**
 * Romane Zimmermann
 * Projet Démineur
 */

package cpo_miniprojet_zimmermann;

public class Cellule {
    private boolean presenceBombe;
    private boolean devoilee;
    private int nbBombesAdjacentes;

    public Cellule() {
        this.presenceBombe = false;
        this.devoilee = false;
        this.nbBombesAdjacentes = 0;
    }
    public void retirerBombe() {
    this.presenceBombe = false;
    }

    public void masquerCellule() {
    this.devoilee = false;
    }

    public boolean getPresenceBombe() {
        return presenceBombe;
    }

    public int getNbBombesAdjacentes() {
        return nbBombesAdjacentes;
    }

    public void placerBombe() {
        this.presenceBombe = true;
    }

    public void revelerCellule() {
        if (!devoilee) {
            this.devoilee = true; // Marquer la cellule comme dévoilée
        }
    }

    public void setNbBombesAdjacentes(int nbBombesAdjacentes) {
        this.nbBombesAdjacentes = nbBombesAdjacentes;
    }

    @Override
    public String toString() {
        if (!devoilee) {
            return "?";
        } else if (presenceBombe) {
            return "B";
        } else if (nbBombesAdjacentes > 0) {
            return String.valueOf(nbBombesAdjacentes);
        } else {
            return " ";
        }
    }

    public boolean estDevoilee() {
        return devoilee;
    }
}
