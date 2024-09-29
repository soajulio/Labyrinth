package Modeles;

import java.awt.Point;
import java.util.*;

public class GreedyBestFirst implements Algorithme {
    private Labyrinthe labyrinthe;

    public GreedyBestFirst(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    @Override
    public List<Point> resoudre() {
        Point depart = labyrinthe.coordonneesDepart();
        Point arrivee = labyrinthe.coordonneesArrivee();

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.hCost));
        Set<Point> closedSet = new HashSet<>();
        Map<Point, Point> cameFrom = new HashMap<>();

        openSet.offer(new Node(depart, heuristic(depart, arrivee)));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            labyrinthe.marquerCaseConsultee(current.point);

            if (current.point.equals(arrivee)) {
                return reconstruireChemin(cameFrom, current.point);
            }

            closedSet.add(current.point);

            for (Point voisin : labyrinthe.getVoisinsAccessibles(current.point)) {
                if (closedSet.contains(voisin)) continue;

                Node voisinNode = new Node(voisin, heuristic(voisin, arrivee));

                if (!openSet.contains(voisinNode)) {
                    cameFrom.put(voisin, current.point);
                    openSet.offer(voisinNode);
                }
            }
        }

        return null; // Pas de chemin trouvé
    }

    private int heuristic(Point a, Point b) {
        // Distance de Manhattan
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private List<Point> reconstruireChemin(Map<Point, Point> cameFrom, Point current) {
        List<Point> chemin = new ArrayList<>();
        while (current != null) {
            chemin.add(0, current);
            current = cameFrom.get(current);
        }
        return chemin;
    }

    private static class Node {
        Point point;
        int hCost;

        Node(Point point, int hCost) {
            this.point = point;
            this.hCost = hCost;
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
