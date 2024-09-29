/**
package Modeles;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Dijkstra implements Algorithme {

    private Labyrinthe labyrinthe;

    public Dijkstra(Labyrinthe labyrinthe) {
        super();
    }

    @Override
    public List<JButton> resoudre() {
        JButton depart = labyrinthe.getDepartButton();
        JButton arrivee = labyrinthe.getArriveeButton();

        PriorityQueue<JButton> file = new PriorityQueue<>(Comparator.comparingInt(b -> (int) b.getClientProperty("distance")));
        Map<JButton, Integer> distances = new HashMap<>();
        Map<JButton, JButton> predecesseurs = new HashMap<>();

        for (JButton[] ligne : labyrinthe.getGrille()) {
            for (JButton bouton : ligne) {
                distances.put(bouton, Integer.MAX_VALUE);
                bouton.putClientProperty("distance", Integer.MAX_VALUE);
            }
        }
        distances.put(depart, 0);
        depart.putClientProperty("distance", 0);
        file.offer(depart);

        while (!file.isEmpty()) {
            JButton courant = file.poll();
            if (courant == arrivee) {
                return reconstruireChemin(predecesseurs, arrivee);
            }

            for (JButton voisin : labyrinthe.getVoisins(courant)) {
                int newDist = distances.get(courant) + 1;
                if (newDist < distances.get(voisin)) {
                    distances.put(voisin, newDist);
                    predecesseurs.put(voisin, courant);
                    voisin.putClientProperty("distance", newDist);
                    file.remove(voisin);
                    file.offer(voisin);
                    voisin.setBackground(Color.YELLOW); // Marquer comme exploré
                    labyrinthe.notifierChangement();
                }
            }
        }

        return null; // Pas de chemin trouvé
    }

    private List<JButton> reconstruireChemin(Map<JButton, JButton> predecesseurs, JButton arrivee) {
        List<JButton> chemin = new ArrayList<>();
        JButton courant = arrivee;
        while (courant != null) {
            chemin.add(0, courant);
            courant = predecesseurs.get(courant);
        }
        return chemin;
    }
} */
