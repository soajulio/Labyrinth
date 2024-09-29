package Controllers.grille;

import Modeles.Labyrinthe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EcouteurGrille implements MouseListener {

    private Labyrinthe labyrinthe;


    public EcouteurGrille(Labyrinthe labyrinthe){
        this.labyrinthe = labyrinthe;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton boutonClique = (JButton) e.getSource();
        System.out.println("Bouton cliqué : " + labyrinthe.getEtatActuel() + " " + labyrinthe.isDepartClicked());
        labyrinthe.setEtatButton(boutonClique);

        if (labyrinthe.getGrille() != null) {
            Point coordonneesDepart = labyrinthe.coordonneesDepart();
            Point coordonneesArrivee = labyrinthe.coordonneesArrivee();
            if (coordonneesDepart != null) {
                System.out.println("Bouton de départ : " + coordonneesDepart);
            } else {
                System.out.println("Aucun bouton de départ trouvé.");
            }
            if (coordonneesArrivee != null) {
                System.out.println("Bouton d'arrivée : " + coordonneesArrivee);
            } else {
                System.out.println("Aucun bouton d'arrivée trouvé.");
            }
        } else {
            System.out.println("La grille n'a pas encore été initialisée.");
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
