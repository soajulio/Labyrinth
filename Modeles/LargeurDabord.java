package Modeles;

import java.awt.Point;
import java.util.*;

public class LargeurDabord implements Algorithme {
    private Labyrinthe labyrinthe;

    public LargeurDabord(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    @Override
    public List<Point> resoudre() {
        Point depart = labyrinthe.coordonneesDepart();
        Point arrivee = labyrinthe.coordonneesArrivee();

        Queue<Point> queue = new LinkedList<>();
        Map<Point, Point> predecesseurs = new HashMap<>();
        Set<Point> visites = new HashSet<>();

        queue.offer(depart);
        visites.add(depart);

        while (!queue.isEmpty()) {
            Point courant = queue.poll();
            labyrinthe.marquerCaseConsultee(courant);

            if (courant.equals(arrivee)) {
                return reconstruireChemin(predecesseurs, arrivee);
            }

            for (Point voisin : labyrinthe.getVoisinsAccessibles(courant)) {
                if (!visites.contains(voisin)) {
                    queue.offer(voisin);
                    visites.add(voisin);
                    predecesseurs.put(voisin, courant);
                }
            }
        }

        return null; // Pas de chemin trouvé
    }

    private List<Point> reconstruireChemin(Map<Point, Point> predecesseurs, Point arrivee) {
        List<Point> chemin = new ArrayList<>();
        Point courant = arrivee;
        while (courant != null) {
            chemin.add(0, courant);
            courant = predecesseurs.get(courant);
        }
        return chemin;
    }
}
