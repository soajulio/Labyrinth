package Modeles;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Dijkstra implements Algorithme {
    private Labyrinthe labyrinthe;

    public Dijkstra(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    @Override
    public List<Point> resoudre() {
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
                return reconstruireChemin(predecesseurs, arrivee);
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

        return null; // Pas de chemin trouvé
    }

    private List<Point> reconstruireChemin(Map<Point, Point> predecesseurs, Point arrivee) {
        List<Point> chemin = new ArrayList<>();
        for (Point at = arrivee; at != null; at = predecesseurs.get(at)) {
            chemin.add(0, at);
        }
        return chemin;
    }

    private static class Node {
        Point point;
        int distance;

        Node(Point p, int d) {
            point = p;
            distance = d;
        }
    }
}
