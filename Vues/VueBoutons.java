package Vues;

import Controllers.algo.EcouteurAlgo;
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
    private EcouteurAlgo ecouteurAlgo;

    public VueBoutons(Labyrinthe labyrinthe, EcouteurBoutons ecouteurBoutons, EcouteurAlgo ecouteurAlgo) {
        this.ecouteurBoutons = ecouteurBoutons;
        this.ecouteurAlgo = ecouteurAlgo;
        this.labyrinthe = labyrinthe;
        this.labyrinthe.addObserver(this);

        phraseAccroche = new JLabel("Composez votre labyrinthe ");
        boutonMur = new JButton("Mur");
        boutonDepart = new JButton("Depart");
        boutonArrivee = new JButton("Arrivee");
        boutonVide = new JButton("Vide");
        comboBoxAlgo = new JComboBox<>(new String[]{"Sélectionner un algo", "Largeur d'abord", "Profondeur d'abord", "Greedy Best First","Dijkstra", "A*", "IDA*"});
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
        comboBoxAlgo.addActionListener(ecouteurAlgo);

        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

