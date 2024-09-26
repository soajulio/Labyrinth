package Controllers.grille;

import Modeles.Labyrinthe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Modeles.Labyrinthe.EtatSelection.*;

public class EcouteurGrille implements ActionListener {

    private Labyrinthe labyrinthe;

    private int x, y;

    public EcouteurGrille(Labyrinthe labyrinthe, int x, int y){
        this.labyrinthe = labyrinthe;
        this.x = x;
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();
        System.out.println("Bouton cliqué : " + x + " " + y + " " + labyrinthe.getEtatActuel() + " " + labyrinthe.isDepartClicked());
        if (labyrinthe.getEtatActuel() == Labyrinthe.EtatSelection.VIDE && labyrinthe.isDepartClicked() == true) {
            boutonClique.setBackground(Color.GREEN);
        }
    }
}
