package Vues;

import Controllers.algo.EcouteurAlgo;
import Controllers.boutons.EcouteurBoutons;
import Controllers.grille.EcouteurGrille;
import Modeles.Labyrinthe;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class VueFenetre extends JFrame implements Observer {

    private Labyrinthe labyrinthe;

    public VueFenetre(Labyrinthe labyrinthe, EcouteurBoutons ecouteurBoutons, EcouteurGrille ecouteurGrille, EcouteurAlgo ecouteurAlgo) {
        this.setTitle("Labyrinthe");
        this.setSize(700, 700);

        this.labyrinthe = labyrinthe;
        VueBoutons vueBoutons = new VueBoutons(labyrinthe, ecouteurBoutons, ecouteurAlgo);
        VueAffichage vueAffichage = new VueAffichage();
        labyrinthe.addObserver(vueAffichage);
        VueGrille vueGrille = new VueGrille(labyrinthe, 10, ecouteurGrille);

        this.add(vueBoutons, BorderLayout.NORTH);
        this.add(vueAffichage, BorderLayout.SOUTH);
        this.add(vueGrille, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
