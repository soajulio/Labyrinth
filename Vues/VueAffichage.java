package Vues;

import Modeles.Labyrinthe;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueAffichage extends JPanel implements Observer {

    private JLabel Time;
    private JLabel Number;
    private JLabel pathFound;
    private JLabel pathLength;

    private float timeExecution;
    private int generatedStated;
    private boolean found;
    private int length;

    public VueAffichage(){
        Time = new JLabel("Time for execution: 0.0 s");
        Number = new JLabel("Number of generated states: 0");
        pathFound = new JLabel("Path found: false");
        pathLength = new JLabel("Path length: 0");

        this.add(Time);
        this.add(Number);
        this.add(pathFound);
        this.add(pathLength);

        this.setVisible(true);
    }

    private void updateLabels() {
        Time.setText("Time for execution: " + timeExecution + " s");
        Number.setText("Number of generated states: " + generatedStated);
        pathFound.setText("Path found: " + found);
        pathLength.setText("Path length: " + length);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Labyrinthe) {
            Labyrinthe labyrinthe = (Labyrinthe) o;
            timeExecution = labyrinthe.getTimeExecution();
            generatedStated = labyrinthe.getGeneratedStates();
            found = labyrinthe.isPathFound();
            length = labyrinthe.getPathLength();
            updateLabels();
        }
    }
}
