package Controllers.grille;

import Modeles.Labyrinthe;

import javax.swing.*;
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
        labyrinthe.logDepartArrivee();
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
