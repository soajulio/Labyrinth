package Vues;

import Controllers.boutons.EcouteurBoutons;
import Modeles.Labyrinthe;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class VueBoutons extends JPanel implements Observer {
    private Labyrinthe labyrinthe;

    private JLabel phraseAccroche;
    private JButton boutonMur;
    private JButton boutonDepart;
    private JButton boutonArrivee;
    private JButton boutonVide;
    private JComboBox<String> comboBoxAlgo;
    private JButton boutonDemarrer;
    private EcouteurBoutons ecouteurBoutons;

    public VueBoutons(Labyrinthe labyrinthe, EcouteurBoutons ecouteurBoutons){
        this.ecouteurBoutons = ecouteurBoutons;
        this.labyrinthe = labyrinthe;
        this.labyrinthe.addObserver(this);

        phraseAccroche = new JLabel("Composez votre labyrinthe ");
        boutonMur = new JButton("Mur");
        boutonDepart = new JButton("Depart");
        boutonArrivee = new JButton("Arrivee");
        boutonVide = new JButton("Vide");
        comboBoxAlgo = new JComboBox<>(new String[]{"Largeur d'abord", "Profondeur d'abord", "Greedy Best First","Dijkstra", "A*", "IDA*"});
        boutonDemarrer = new JButton("Demarrer");

        boutonMur.setBackground(Color.BLACK);
        boutonMur.setForeground(Color.WHITE);
        boutonDepart.setBackground(Color.GREEN);
        boutonArrivee.setBackground(Color.RED);
        boutonVide.setBackground(Color.WHITE);

        this.add(phraseAccroche);
        this.add(boutonMur);
        this.add(boutonDepart);
        this.add(boutonArrivee);
        this.add(boutonVide);
        this.add(comboBoxAlgo);
        this.add(boutonDemarrer);

        boutonDepart.addMouseListener(ecouteurBoutons);
        boutonArrivee.addMouseListener(ecouteurBoutons);
        boutonMur.addMouseListener(ecouteurBoutons);
        boutonVide.addMouseListener(ecouteurBoutons);
        boutonDemarrer.addMouseListener(ecouteurBoutons);

        this.setVisible(true);
    }

    public JButton getBoutonMur() {
        return boutonMur;
    }

    public JButton getBoutonDepart() {
        return boutonDepart;
    }

    public JButton getBoutonArrivee() {
        return boutonArrivee;
    }

    public JButton getBoutonVide() {
        return boutonVide;
    }

    public JComboBox<String> getComboBoxAlgo() {
        return comboBoxAlgo;
    }

    public JButton getBoutonDemarrer() {
        return boutonDemarrer;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

