package Vues;

import Controllers.grille.EcouteurGrille;
import Modeles.Labyrinthe;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;

public class VueGrille extends JPanel implements Observer {

    private Labyrinthe labyrinthe;
    private JButton[][] grille;

    public VueGrille(Labyrinthe labyrinthe, int taille) {
        this.labyrinthe = labyrinthe;
        this.labyrinthe.addObserver(this);
        setLayout(new GridLayout(taille, taille));
        grille = new JButton[taille][taille];

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = new JButton();
                grille[i][j].setPreferredSize(new Dimension(30, 30));
                grille[i][j].addActionListener(new EcouteurGrille(labyrinthe, i, j));
                add(grille[i][j]);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
