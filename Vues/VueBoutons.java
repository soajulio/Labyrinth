package Vues;

import Controllers.boutons.EcouteurBoutons;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class VueBoutons extends JPanel implements Observer {
    private JLabel phraseAccroche;
    private JButton boutonMur;
    private JButton boutonDepart;
    private JButton boutonArrivee;
    private JButton boutonVide;
    private JComboBox<String> comboBoxAlgo;
    private JButton boutonDemarrer;
    private EcouteurBoutons ecouteurBoutons;

    public VueBoutons(EcouteurBoutons ecouteurBoutons){
        this.ecouteurBoutons = ecouteurBoutons;
        phraseAccroche = new JLabel("Composez votre labyrinthe ");
        boutonMur = new JButton("Mur");
        boutonDepart = new JButton("Depart");
        boutonArrivee = new JButton("Arrivee");
        boutonVide = new JButton("Vide");
        comboBoxAlgo = new JComboBox<String>();
        boutonDemarrer = new JButton("Demarrer");

        this.add(phraseAccroche);
        this.add(boutonMur);
        this.add(boutonDepart);
        this.add(boutonArrivee);
        this.add(boutonVide);
        this.add(comboBoxAlgo);
        this.add(boutonDemarrer);

        boutonDemarrer.addMouseListener(ecouteurBoutons);
        boutonMur.addMouseListener(ecouteurBoutons);
        boutonArrivee.addMouseListener(ecouteurBoutons);
        boutonDepart.addMouseListener(ecouteurBoutons);
        boutonVide.addMouseListener(ecouteurBoutons);
        comboBoxAlgo.addMouseListener(ecouteurBoutons);

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

