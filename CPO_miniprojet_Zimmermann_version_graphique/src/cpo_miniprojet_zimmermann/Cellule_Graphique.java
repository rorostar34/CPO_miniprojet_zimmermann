/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpo_miniprojet_zimmermann;

import javax.swing.JButton;

/**
 *
 * @author 33669
 */
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
    