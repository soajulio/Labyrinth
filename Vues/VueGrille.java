package Vues;

import Modeles.Case;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;

public class VueGrille extends JPanel implements Observer {

    private int widht = 600;
    private int height = 600;
    private final int paddingGrille = 5;
    private final int paddingCase = 2;
    private Case[][] grille;


    public VueGrille(){
    }

    // Create the grid with case
    public void createGrille(int taille){
        grille = new Case[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = new Case(i, j);
            }
        }
    }

    // Affiche la grille
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int taille = grille.length;
        int tailleCase = (widht - paddingGrille) / taille;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                Case c = grille[i][j];
                if (c.isMur()) {
                    g.setColor(Color.BLACK);
                } else if (c.isDepart()) {
                    g.setColor(Color.RED);
                } else if (c.isArrivee()) {
                    g.setColor(Color.GREEN);
                } else if (c.isVide()) {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i * tailleCase + paddingCase, j * tailleCase + paddingCase, tailleCase - paddingCase, tailleCase - paddingCase);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
