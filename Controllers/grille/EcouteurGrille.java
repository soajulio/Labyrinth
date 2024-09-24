package Controllers.grille;

import Modeles.Labyrinthe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurGrille implements ActionListener {

    private Labyrinthe labyrinthe;

    public EcouteurGrille(Labyrinthe labyrinthe){
        this.labyrinthe = labyrinthe;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
