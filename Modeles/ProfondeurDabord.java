package Modeles;

import java.awt.Point;
import java.util.*;

public class ProfondeurDabord implements IRecherche {
    private Labyrinthe labyrinthe;
    private Set<Point> visites;
    private Map<Point, Point> predecesseurs;
    private Point arrivee;
    private boolean cheminTrouve;
    private long tempsExecution;
    private int noeudsGeneres;
    private int longueurChemin;
    private long debut;

    public ProfondeurDabord(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
        this.visites = new HashSet<>();
        this.predecesseurs = new HashMap<>();
        this.cheminTrouve = false;
    }

    @Override
    public List<Point> resoudre() {
        debut = System.currentTimeMillis();
        noeudsGeneres = 0;
        cheminTrouve = false;

        Point depart = labyrinthe.coordonneesDepart();
        this.arrivee = labyrinthe.coordonneesArrivee();

        if (depart == null || arrivee == null) {
            mettreAJourStatistiques();
            return null; // Pas de départ ou d'arrivée défini
        }

        dfs(depart);

        if (cheminTrouve) {
            List<Point> chemin = reconstruireChemin();
            longueurChemin = chemin.size() - 1; // -1 car on ne compte pas le départ
            mettreAJourStatistiques();
            return chemin;
        } else {
            mettreAJourStatistiques();
            return null; // Pas de chemin trouvé
        }
    }

    private void dfs(Point courant) {
        visites.add(courant);
        noeudsGeneres++;
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

    private void mettreAJourStatistiques() {
        tempsExecution = System.currentTimeMillis() - debut;
        labyrinthe.setTimeExecution(tempsExecution / 1000f);  // Convertir en secondes
        labyrinthe.setGeneratedStates(noeudsGeneres);
        labyrinthe.setPathFound(cheminTrouve);
        labyrinthe.setPathLength(longueurChemin);
    }
}