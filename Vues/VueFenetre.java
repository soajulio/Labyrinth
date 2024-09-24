package Vues;

import Controllers.boutons.EcouteurBoutons;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class VueFenetre extends JFrame implements Observer {

    public VueFenetre(EcouteurBoutons ecouteurBoutons) {
        this.setTitle("Labyrinthe");
        this.setSize(614, 700);

        VueBoutons vueBoutons = new VueBoutons(ecouteurBoutons);
        VueAffichage vueAffichage = new VueAffichage();
        VueGrille vueGrille = new VueGrille();
        vueGrille.createGrille(10);

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
