package Vues;

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
        Time = new JLabel("Time for execution: " + timeExecution + " s ");
        Number = new JLabel(" Number of generated states : " + generatedStated);
        pathFound = new JLabel(" Path found : " + found);
        pathLength = new JLabel(" Path length : " + length);

        this.add(Time);
        this.add(Number);
        this.add(pathFound);
        this.add(pathLength);

        this.setVisible(true);
    }
    @Override
    public void update(Observable o, Object arg) {

    }
}
