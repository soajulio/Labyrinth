package Controllers.boutons;

import Modeles.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EcouteurBoutons implements MouseListener {
    private Labyrinthe labyrinthe;

    public EcouteurBoutons(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if(o instanceof JButton){
            JButton bouton = (JButton) o;
            labyrinthe.setEtatBoutonMenu(bouton);
            labyrinthe.doAlgo(bouton);
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