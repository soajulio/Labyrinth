package Modeles;

import java.awt.Point;
import java.util.*;

public class ProfondeurDabord implements Algorithme {
    private Labyrinthe labyrinthe;
    private Set<Point> visites;
    private Map<Point, Point> predecesseurs;
    private Point arrivee;
    private boolean cheminTrouve;

    public ProfondeurDabord(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
        this.visites = new HashSet<>();
        this.predecesseurs = new HashMap<>();
        this.cheminTrouve = false;
    }

    @Override
    public List<Point> resoudre() {
        Point depart = labyrinthe.coordonneesDepart();
        this.arrivee = labyrinthe.coordonneesArrivee();

        if (depart == null || arrivee == null) {
            return null; // Pas de départ ou d'arrivée défini
        }

        dfs(depart);

        if (cheminTrouve) {
            return reconstruireChemin();
        } else {
            return null; // Pas de chemin trouvé
        }
    }

    private void dfs(Point courant) {
        visites.add(courant);
        labyrinthe.marquerCaseConsultee(courant);

        if (courant.equals(arrivee)) {
            cheminTrouve = true;
            return;
        }

        for (Point voisin : labyrinthe.getVoisinsAccessibles(courant)) {
            if (!visites.contains(voisin) && !cheminTrouve) {
                predecesseurs.put(voisin, courant);
                dfs(voisin);
            }
        }
    }

    private List<Point> reconstruireChemin() {
        List<Point> chemin = new ArrayList<>();
        Point courant = arrivee;
        while (courant != null) {
            chemin.add(0, courant);
            courant = predecesseurs.get(courant);
        }
        return chemin;
    }
}
