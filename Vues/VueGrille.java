package Vues;

import Controllers.grille.EcouteurGrille;
import Modeles.Labyrinthe;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;

public class VueGrille extends JPanel implements Observer {

    private JButton[][] grille;
    private EcouteurGrille ecouteurGrille;

    public VueGrille(Labyrinthe labyrinthe, int taille, EcouteurGrille ecouteurGrille) {
        this.ecouteurGrille = ecouteurGrille;
        setLayout(new GridLayout(taille, taille));
        grille = new JButton[taille][taille];

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = new JButton();
                grille[i][j].setPreferredSize(new Dimension(30, 30));
                grille[i][j].setBackground(Color.WHITE);
                add(grille[i][j]);
            }
        }
        // Transmettre la grille à l'objet Labyrinthe
        labyrinthe.setGrille(grille);
        System.out.println("Grille transmise à l'objet Labyrinthe");

        // Ajout des écouteurs après l'initialisation de la grille
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j].addMouseListener(ecouteurGrille);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}