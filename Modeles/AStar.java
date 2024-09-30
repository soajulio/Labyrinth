package Modeles;

import java.awt.Point;
import java.util.*;

public class AStar implements IRecherche {
    private Labyrinthe labyrinthe;
    private long tempsExecution;
    private int noeudsGeneres;
    private boolean cheminTrouve;
    private int longueurChemin;
    private long debut;

    public AStar(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    @Override
    public List<Point> resoudre() {
        debut = System.currentTimeMillis();
        noeudsGeneres = 0;
        cheminTrouve = false;

        Point depart = labyrinthe.coordonneesDepart();
        Point arrivee = labyrinthe.coordonneesArrivee();

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fCost));
        Set<Point> closedSet = new HashSet<>();
        Map<Point, Point> cameFrom = new HashMap<>();
        Map<Point, Integer> gScore = new HashMap<>();
        Map<Point, Integer> fScore = new HashMap<>();

        openSet.offer(new Node(depart, 0, heuristic(depart, arrivee)));
        gScore.put(depart, 0);
        fScore.put(depart, heuristic(depart, arrivee));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            labyrinthe.marquerCaseConsultee(current.point);

            if (current.point.equals(arrivee)) {
                List<Point> chemin = reconstruireChemin(cameFrom, current.point);
                cheminTrouve = true;
                longueurChemin = chemin.size() - 1;
                mettreAJourStatistiques();
                return chemin;
            }

            closedSet.add(current.point);

            for (Point voisin : labyrinthe.getVoisinsAccessibles(current.point)) {
                if (closedSet.contains(voisin)) continue;

                int tentativeGScore = gScore.get(current.point) + 1;

                if (!openSet.contains(new Node(voisin, 0, 0)) || tentativeGScore < gScore.getOrDefault(voisin, Integer.MAX_VALUE)) {
                    cameFrom.put(voisin, current.point);
                    gScore.put(voisin, tentativeGScore);
                    int fScoreVoisin = gScore.get(voisin) + heuristic(voisin, arrivee);
                    fScore.put(voisin, fScoreVoisin);

                    Node voisinNode = new Node(voisin, gScore.get(voisin), fScoreVoisin);
                    if (!openSet.contains(voisinNode)) {
                        openSet.offer(voisinNode);
                    }
                }
            }
        }

        mettreAJourStatistiques();
        return null; // Pas de chemin trouvé
    }

    private int heuristic(Point a, Point b) {
        // Distance de Manhattan
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private List<Point> reconstruireChemin(Map<Point, Point> cameFrom, Point current) {
        List<Point> chemin = new ArrayList<>();
        chemin.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            chemin.add(0, current);
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
        int gCost;
        int fCost;

        Node(Point point, int gCost, int fCost) {
            this.point = point;
            this.gCost = gCost;
            this.fCost = fCost;
            noeudsGeneres++;  // Incrémenter le compteur à chaque création de nœud
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return point.equals(node.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(point);
        }
    }
}
