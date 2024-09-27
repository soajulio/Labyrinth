package Controllers.boutons;

import Controllers.grille.EcouteurGrille;
import Modeles.Labyrinthe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;

public class EcouteurBoutons implements ActionListener {
    private Labyrinthe labyrinthe;

    public EcouteurBoutons(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o instanceof JButton){
            JButton bouton = (JButton) o;
            switch (bouton.getText()) {
                case "Depart":
                    labyrinthe.setEtatActuel((Labyrinthe.EtatSelection.DEPART));
                    labyrinthe.setDepartClicked(true);
                    System.out.println("Le bouton départ est sélectionné" + " " + labyrinthe.getEtatActuel() + " " + labyrinthe.isDepartClicked());
                    break;
                case "Arrivee":
                    labyrinthe.setEtatActuel((Labyrinthe.EtatSelection.ARRIVEE));
                    labyrinthe.setArriveeClicked(true);
                    System.out.println("Le bouton arrivée est sélectionné" + " " + labyrinthe.getEtatActuel() + " " + labyrinthe.isArriveeClicked());
                    break;
                case "Mur":
                    labyrinthe.setEtatActuel((Labyrinthe.EtatSelection.MUR));
                    labyrinthe.setMurClicked(true);
                    System.out.println("Le bouton mur est sélectionné" + " " + labyrinthe.getEtatActuel() + " " + labyrinthe.isMurClicked());
                    break;
                case "Vide":
                    labyrinthe.setEtatActuel((Labyrinthe.EtatSelection.VIDE));
                    labyrinthe.setVideClicked(true);
                    System.out.println("Le bouton vide est sélectionné" + " " + labyrinthe.getEtatActuel() + " " + labyrinthe.isVideClicked());
                    break;
                case "Demarrer":
                    labyrinthe.setEtatActuel((Labyrinthe.EtatSelection.DEMARRER));
                    System.out.println("Le bouton démarrer est sélectionné");
                    break;
            }
        }
    }
}