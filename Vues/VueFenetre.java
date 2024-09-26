package Vues;

import Controllers.boutons.EcouteurBoutons;
import Modeles.Labyrinthe;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class VueFenetre extends JFrame implements Observer {

    private Labyrinthe labyrinthe;

    public VueFenetre(Labyrinthe labyrinthe, EcouteurBoutons ecouteurBoutons) {
        this.setTitle("Labyrinthe");
        this.setSize(700, 700);

        this.labyrinthe = new Labyrinthe();
        VueBoutons vueBoutons = new VueBoutons(labyrinthe, ecouteurBoutons);
        VueAffichage vueAffichage = new VueAffichage();
        VueGrille vueGrille = new VueGrille(this.labyrinthe, 10);

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
