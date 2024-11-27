package cpo_miniprojet_zimmermann;

import java.util.Scanner;

public class Partie {
    private GrilleDeJeu grille;
    private boolean jeuTermine;

    public Partie(int nbLignes, int nbColonnes, int nbBombes) {
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        this.jeuTermine = false;
    }

    public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in);

        grille.placerBombesAleatoirement();
        grille.calculerBombesAdjacentes();

        System.out.println("Bienvenue dans le jeu de Démineur !");
        System.out.println("Voici la grille :");

        while (!jeuTermine) {
            System.out.println(grille);
            System.out.print("Entrez une ligne : ");
            int ligne = scanner.nextInt();
            System.out.print("Entrez une colonne : ");
            int colonne = scanner.nextInt();

            revelerCelluleJoueur(ligne, colonne);

            if (grille.toutesCellulesRevelees()) {
                System.out.println("Félicitations, vous avez gagné !");
                jeuTermine = true;
            }
        }

        scanner.close();
    }

    private void revelerCelluleJoueur(int ligne, int colonne) {
        if (ligne < 0 || ligne >= grille.getNbLignes() || colonne < 0 || colonne >= grille.getNbColonnes()) {
            System.out.println("Coordonnées invalides. Essayez à nouveau.");
            return;
        }

        // Révéler la cellule
        grille.revelerCelluleParCoordonnees(ligne, colonne);

        // Vérifier si la cellule contient une bombe
        if (grille.matriceCellules[ligne][colonne].getPresenceBombe()) {
            System.out.println("BOUM ! Vous avez touché une bombe. Game Over !");
            jeuTermine = true;
        } else {
            // Obtenir le nombre de bombes adjacentes
            int bombesAdjacentes = grille.matriceCellules[ligne][colonne].getNbBombesAdjacentes();

            // Fournir des messages spécifiques au joueur
            if (bombesAdjacentes > 0) {
                System.out.println("Attention ! Il y a " + bombesAdjacentes + " bombe(s) autour de cette position.");
            } else {
                System.out.println("Vous êtes en sécurité. Il n'y a aucune bombe autour de cette position.");
            }
        }
    }
}
