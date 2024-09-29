package Controllers.algo;

import Modeles.Labyrinthe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurAlgo implements ActionListener {
    private Labyrinthe labyrinthe;

    public EcouteurAlgo(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
        String algoSelectionne = (String) comboBox.getSelectedItem();
        labyrinthe.setAlgorithmeSelectionne(algoSelectionne);
    }
}
