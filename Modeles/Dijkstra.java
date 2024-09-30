package Modeles;

import java.awt.Point;
import java.util.*;

public class Dijkstra implements IRecherche {
    private Labyrinthe labyrinthe;
    private long tempsExecution;
    private int noeudsGeneres;
    private boolean cheminTrouve;
    private int longueurChemin;
    private long debut;

    public Dijkstra(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    @Override
    public List<Point> resoudre() {
        debut = System.currentTimeMillis();
        noeudsGeneres = 0;
        cheminTrouve = false;

        Point depart = labyrinthe.coordonneesDepart();
        Point arrivee = labyrinthe.coordonneesArrivee();

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        Map<Point, Integer> distances = new HashMap<>();
        Map<Point, Point> predecesseurs = new HashMap<>();

        queue.offer(new Node(depart, 0));
        distances.put(depart, 0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            labyrinthe.marquerCaseConsultee(current.point);

            if (current.point.equals(arrivee)) {
                List<Point> chemin = reconstruireChemin(predecesseurs, arrivee);
                cheminTrouve = true;
                longueurChemin = chemin.size() - 1;  // -1 car on ne compte pas le départ
                mettreAJourStatistiques();
                return chemin;
            }

            for (Point voisin : labyrinthe.getVoisinsAccessibles(current.point)) {
                int newDist = distances.get(current.point) + 1;
                if (newDist < distances.getOrDefault(voisin, Integer.MAX_VALUE)) {
                    distances.put(voisin, newDist);
                    predecesseurs.put(voisin, current.point);
                    queue.offer(new Node(voisin, newDist));
                }
            }
        }

        mettreAJourStatistiques();
        return null; // Pas de chemin trouvé
    }

    private List<Point> reconstruireChemin(Map<Point, Point> predecesseurs, Point arrivee) {
        List<Point> chemin = new ArrayList<>();
        for (Point at = arrivee; at != null; at = predecesseurs.get(at)) {
            chemin.add(0, at);
        }
        return chemin;
    }

    private void mettreAJourStatistiques() {
        tempsExecution = System.currentTimeMillis() - debut;
        labyrinthe.setTimeExecution(tempsExecution / 1000f);  // Convertir en secondes
        labyrinthe.setGeneratedStates(noeudsGeneres);
        labyrinthe.setPathFound(cheminTrouve);
        labyrinthe.setPathLength(longueurChemin);
    }

    private class Node {
        Point point;
        int distance;

        Node(Point p, int d) {
            point = p;
            distance = d;
            noeudsGeneres++;  // Incrémenter le compteur à chaque création de nœud
        }
    }
}