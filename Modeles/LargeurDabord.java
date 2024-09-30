package Modeles;

import java.awt.Point;
import java.util.*;

public class LargeurDabord implements IRecherche {
    private Labyrinthe labyrinthe;
    private long tempsExecution;
    private int noeudsGeneres;
    private boolean cheminTrouve;
    private int longueurChemin;
    private long debut;

    public LargeurDabord(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    @Override
    public List<Point> resoudre() {
        debut = System.currentTimeMillis();
        noeudsGeneres = 0;
        cheminTrouve = false;

        Point depart = labyrinthe.coordonneesDepart();
        Point arrivee = labyrinthe.coordonneesArrivee();

        Queue<Point> queue = new LinkedList<>();
        Map<Point, Point> predecesseurs = new HashMap<>();
        Set<Point> visites = new HashSet<>();

        queue.offer(depart);
        visites.add(depart);
        noeudsGeneres++;

        while (!queue.isEmpty()) {
            Point courant = queue.poll();
            labyrinthe.marquerCaseConsultee(courant);

            if (courant.equals(arrivee)) {
                List<Point> chemin = reconstruireChemin(predecesseurs, arrivee);
                cheminTrouve = true;
                longueurChemin = chemin.size() - 1; // -1 car on ne compte pas le départ
                mettreAJourStatistiques();
                return chemin;
            }

            for (Point voisin : labyrinthe.getVoisinsAccessibles(courant)) {
                if (!visites.contains(voisin)) {
                    queue.offer(voisin);
                    visites.add(voisin);
                    predecesseurs.put(voisin, courant);
                    noeudsGeneres++;
                }
            }
        }

        mettreAJourStatistiques();
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

    private void mettreAJourStatistiques() {
        tempsExecution = System.currentTimeMillis() - debut;
        labyrinthe.setTimeExecution(tempsExecution / 1000f);  // Convertir en secondes
        labyrinthe.setGeneratedStates(noeudsGeneres);
        labyrinthe.setPathFound(cheminTrouve);
        labyrinthe.setPathLength(longueurChemin);
    }
}