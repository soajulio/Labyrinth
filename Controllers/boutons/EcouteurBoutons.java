package Controllers.boutons;

import Controllers.grille.EcouteurGrille;
import Modeles.*;

import javax.swing.*;
import java.awt.Point;
import java.awt.Color;
import java.util.List;
import Vues.VueAffichage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;

public class EcouteurBoutons implements MouseListener {
    private Labyrinthe labyrinthe;
    private JComboBox<String> algoComboBox;

    public EcouteurBoutons(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if(o instanceof JButton){
            JButton bouton = (JButton) o;
            labyrinthe.setEtatBoutonMenu(bouton);

            if(bouton.getText().equals("Demarrer")){
                labyrinthe.reinitialiserGrille();
                String algoSelectionne = labyrinthe.getAlgorithmeSelectionne();
                Algorithme algo;

                switch (algoSelectionne) {
                    case "Dijkstra":
                        algo = new Dijkstra(labyrinthe);
                        List<Point> cheminDijkstra = algo.resoudre();
                        if (cheminDijkstra != null) {
                            labyrinthe.setChemin(cheminDijkstra);
                            System.out.println("Algo Dijkstra réussi, chemin trouvé");
                        } else {
                            System.out.println("Algo Dijkstra terminé, pas de chemin trouvé");
                        }
                        break;
                    case "A*":
                        algo = new AStar(labyrinthe);
                        List<Point> cheminAStar = algo.resoudre();
                        if (cheminAStar != null) {
                            labyrinthe.setChemin(cheminAStar);
                            System.out.println("Algo A* réussi, chemin trouvé");
                        } else {
                            System.out.println("Algo A* terminé, pas de chemin trouvé");
                        }
                        break;
                    case "Largeur d'abord":
                        algo = new LargeurDabord(labyrinthe);
                        List<Point> cheminLargeurDabord = algo.resoudre();
                        if (cheminLargeurDabord != null) {
                            labyrinthe.setChemin(cheminLargeurDabord);
                            System.out.println("Algo Largeur d'abord réussi, chemin trouvé");
                        } else {
                            System.out.println("Algo Largeur d'abord terminé, pas de chemin trouvé");
                        }
                        break;
                    case "Profondeur d'abord":
                        algo = new ProfondeurDabord(labyrinthe);
                        List<Point> cheminProfondeurDabord = algo.resoudre();
                        if (cheminProfondeurDabord != null) {
                            labyrinthe.setChemin(cheminProfondeurDabord);
                            System.out.println("Algo Profondeur d'abord réussi, chemin trouvé");
                        } else {
                            System.out.println("Algo Profondeur d'abord terminé, pas de chemin trouvé");
                        }
                        break;
                    case "Greedy Best First":
                        algo = new GreedyBestFirst(labyrinthe);
                        List<Point> cheminGreedyBestFirst = algo.resoudre();
                        if (cheminGreedyBestFirst != null) {
                            labyrinthe.setChemin(cheminGreedyBestFirst);
                            System.out.println("Algo Greedy Best First réussi, chemin trouvé");
                        } else {
                            System.out.println("Algo Greedy Best First terminé, pas de chemin trouvé");
                        }
                        break;
                    case "IDA*":
                        algo = new IDAStar(labyrinthe);
                        List<Point> cheminIDAStar = algo.resoudre();
                        if (cheminIDAStar != null) {
                            labyrinthe.setChemin(cheminIDAStar);
                            System.out.println("Algo IDA* réussi, chemin trouvé");
                        } else {
                            System.out.println("Algo IDA* terminé, pas de chemin trouvé");
                        }
                        break;
                    default:
                        System.out.println("Algorithme non reconnu");
                        return;
                }
            }
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