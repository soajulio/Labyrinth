package Modeles;

import java.awt.Point;
import java.util.*;

public class IDAStar implements IRecherche {
    private Labyrinthe labyrinthe;
    private Point arrivee;
    private int noeudsGeneres;
    private long tempsExecution;
    private boolean cheminTrouve;
    private int longueurChemin;
    private long debut;

    public IDAStar(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
        this.noeudsGeneres = 0;
    }

    @Override
    public List<Point> resoudre() {
        debut = System.currentTimeMillis();
        noeudsGeneres = 0;
        cheminTrouve = false;

        Point depart = labyrinthe.coordonneesDepart();
        this.arrivee = labyrinthe.coordonneesArrivee();

        int limite = heuristic(depart, arrivee);
        while (true) {
            Pair<List<Point>, Integer> resultat = recherche(depart, 0, limite, new ArrayList<>());
            if (resultat.getFirst() != null) {
                cheminTrouve = true;
                longueurChemin = resultat.getFirst().size() - 1;
                mettreAJourStatistiques();
                return resultat.getFirst(); // Chemin trouvé
            }
            if (resultat.getSecond() == Integer.MAX_VALUE) {
                mettreAJourStatistiques();
                return null; // Pas de chemin trouvé
            }
            limite = resultat.getSecond(); // Nouvelle limite pour la prochaine itération
        }
    }

    private Pair<List<Point>, Integer> recherche(Point courant, int g, int limite, List<Point> chemin) {
        noeudsGeneres++;
        labyrinthe.marquerCaseConsultee(courant);

        int f = g + heuristic(courant, arrivee);
        if (f > limite) {
            return new Pair<>(null, f);
        }
        if (courant.equals(arrivee)) {
            chemin.add(courant);
            return new Pair<>(chemin, limite);
        }

        int min = Integer.MAX_VALUE;
        for (Point voisin : labyrinthe.getVoisinsAccessibles(courant)) {
            if (!chemin.contains(voisin)) {
                chemin.add(courant);
                Pair<List<Point>, Integer> resultat = recherche(voisin, g + 1, limite, new ArrayList<>(chemin));
                if (resultat.getFirst() != null) {
                    return resultat;
                }
                min = Math.min(min, resultat.getSecond());
                chemin.remove(chemin.size() - 1);
            }
        }
        return new Pair<>(null, min);
    }

    private int heuristic(Point a, Point b) {
        // Distance de Manhattan
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private void mettreAJourStatistiques() {
        tempsExecution = System.currentTimeMillis() - debut;
        labyrinthe.setTimeExecution(tempsExecution / 1000f);  // Convertir en secondes
        labyrinthe.setGeneratedStates(noeudsGeneres);
        labyrinthe.setPathFound(cheminTrouve);
        labyrinthe.setPathLength(longueurChemin);
    }

    private static class Pair<T, U> {
        private final T first;
        private final U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() { return first; }
        public U getSecond() { return second; }
    }
}