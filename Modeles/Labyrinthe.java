package Modeles;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Observable;

public class Labyrinthe extends Observable {

    public enum EtatSelection { DEPART, ARRIVEE, MUR, VIDE, DEMARRER }
    private EtatSelection etatActuel = EtatSelection.VIDE;

    public boolean departClicked = false;
    private boolean arriveeClicked = false;
    private boolean murClicked = false;
    private boolean videClicked = false;

    private JButton buttonTempDepart;
    private JButton buttonTempArrivee;

    private JButton[][] grille;

    private String algorithmeSelectionne;

    private float timeExecution;
    private int generatedStates;
    private boolean pathFound;
    private int pathLength;

    public boolean isDepartClicked() {
        return departClicked;
    }

    public void setDepartClicked(boolean departClicked) {
        this.departClicked = departClicked;
        setChanged();
        notifyObservers();
    }

    public boolean isArriveeClicked() {
        return arriveeClicked;
    }

    public void setArriveeClicked(boolean arriveeClicked) {
        this.arriveeClicked = arriveeClicked;
        setChanged();
        notifyObservers();
    }

    public boolean isMurClicked() {
        return murClicked;
    }

    public void setMurClicked(boolean murClicked) {
        this.murClicked = murClicked;
        setChanged();
        notifyObservers();
    }

    public boolean isVideClicked() {
        return videClicked;
    }

    public void setVideClicked(boolean videClicked) {
        this.videClicked = videClicked;
        setChanged();
        notifyObservers();
    }

    // Définition des états des boutons de la grille
    public void setEtatButton(JButton button) {
        switch (this.getEtatActuel()) {
            case DEPART:
                if (this.isDepartClicked()) {
                    if (buttonTempDepart != null) {
                        buttonTempDepart.setBackground(Color.WHITE);
                    }
                    buttonTempDepart = button;
                    buttonTempDepart.setBackground(Color.GREEN);
                    this.setEtatActuel(EtatSelection.DEPART);
                    this.setDepartClicked(false);
                    setChanged();
                    notifyObservers();
                } else {
                    this.setEtatActuel(EtatSelection.VIDE);
                }
                break;
            case ARRIVEE:
                if(this.isArriveeClicked()){
                    if (buttonTempArrivee != null) {
                        buttonTempArrivee.setBackground(Color.WHITE);
                    }
                    buttonTempArrivee = button;
                    buttonTempArrivee.setBackground(Color.RED);
                    this.setEtatActuel(EtatSelection.ARRIVEE);
                    this.setArriveeClicked(false);
                    setChanged();
                    notifyObservers();
                } else {
                    this.setEtatActuel(EtatSelection.VIDE);
                }
                break;
            case MUR:
                if(this.isMurClicked()){
                    button.setBackground(Color.BLACK);
                    this.setEtatActuel(EtatSelection.MUR);
                    setChanged();
                    notifyObservers();
                } else {
                    this.setEtatActuel(EtatSelection.VIDE);
                }
                break;
            case VIDE:
                if(this.isVideClicked()){
                    button.setBackground(Color.WHITE);
                    this.setEtatActuel(EtatSelection.VIDE);
                    setChanged();
                    notifyObservers();
                } else {
                    this.setEtatActuel(EtatSelection.VIDE);
                }
                break;
        }
    }

    public void setEtatActuel(EtatSelection etat) {
        this.etatActuel = etat;
        setChanged();
        notifyObservers();
    }

    public EtatSelection getEtatActuel() {
        return etatActuel;
    }

    // Logique pour ecoûter les boutons du menu
    public void setEtatBoutonMenu(JButton bouton) {
        switch (bouton.getText()) {
            case "Depart":
                this.setEtatActuel((Labyrinthe.EtatSelection.DEPART));
                this.setDepartClicked(true);
                System.out.println("Le bouton départ est sélectionné" + " " + this.getEtatActuel() + " " + this.isDepartClicked());
                break;
            case "Arrivee":
                this.setEtatActuel((Labyrinthe.EtatSelection.ARRIVEE));
                this.setArriveeClicked(true);
                System.out.println("Le bouton arrivée est sélectionné" + " " + this.getEtatActuel() + " " + this.isArriveeClicked());
                break;
            case "Mur":
                this.setEtatActuel((Labyrinthe.EtatSelection.MUR));
                this.setMurClicked(true);
                System.out.println("Le bouton mur est sélectionné" + " " + this.getEtatActuel() + " " + this.isMurClicked());
                break;
            case "Vide":
            case "":
                this.setEtatActuel((Labyrinthe.EtatSelection.VIDE));
                this.setVideClicked(true);
                System.out.println("Le bouton vide est sélectionné" + " " + this.getEtatActuel() + " " + this.isVideClicked());
                break;
            case "Demarrer":
                this.setEtatActuel((Labyrinthe.EtatSelection.DEMARRER));
                System.out.println("Le bouton démarrer est sélectionné");
                break;
        }
    }

    public void setGrille(JButton[][] grille) {
        this.grille = grille;
        System.out.println("Grille initialisée avec succès !");
    }

    public JButton[][] getGrille() {
        return grille;
    }

    // Méthode retournant Coordonnees bouton de départ
    public Point coordonneesDepart() {
        if (this.grille == null) {
            throw new IllegalStateException("La grille n'a pas été initialisée.");
        }
        for(int i = 0; i < grille.length; i++) {
            for(int j = 0; j < grille[i].length; j++) {
                if(grille[i][j].getBackground().equals(Color.GREEN)) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    // Méthode retournant Coordonnees bouton d'arrivée
    public Point coordonneesArrivee() {
        if(this.grille == null) {
            throw new IllegalStateException("La grille n'a pas été initialisée.");
        }
        for(int i = 0; i < grille.length; i++) {
            for(int j = 0; j < grille[i].length; j++) {
                if(grille[i][j].getBackground().equals(Color.RED)) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    // Méthode pour afficher les coordonnées des boutons de départ et d'arrivée
    public void logDepartArrivee() {
        if (this.getGrille() != null) {
            Point coordonneesDepart = this.coordonneesDepart();
            Point coordonneesArrivee = this.coordonneesArrivee();
            if (coordonneesDepart != null) {
                System.out.println("Bouton de départ : " + coordonneesDepart);
            } else {
                System.out.println("Aucun bouton de départ trouvé.");
            }
            if (coordonneesArrivee != null) {
                System.out.println("Bouton d'arrivée : " + coordonneesArrivee);
            } else {
                System.out.println("Aucun bouton d'arrivée trouvé.");
            }
        } else {
            System.out.println("La grille n'a pas encore été initialisée.");
        }
    }

    public boolean estMur(int x, int y) {
        return grille[x][y].getBackground().equals(Color.BLACK);
    }

    public List<Point> getVoisinsAccessibles(Point p) {
        List<Point> voisins = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // droite, bas, gauche, haut
        for (int[] dir : directions) {
            int newX = p.x + dir[0];
            int newY = p.y + dir[1];
            if (newX >= 0 && newX < grille.length && newY >= 0 && newY < grille[0].length && !estMur(newX, newY)) {
                voisins.add(new Point(newX, newY));
            }
        }
        return voisins;
    }

    public void setAlgorithmeSelectionne(String algorithmeSelectionne) {
        this.algorithmeSelectionne = algorithmeSelectionne;
        setChanged();
        notifyObservers();
    }

    public String getAlgorithmeSelectionne() {
        return algorithmeSelectionne;
    }

    // Méthode pour mettre à jour la grille avec le nouveau chemin
    public void setChemin(List<Point> chemin) {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                Color couleurActuelle = grille[i][j].getBackground();
                if (couleurActuelle != Color.YELLOW && couleurActuelle != Color.GREEN &&
                        couleurActuelle != Color.RED && couleurActuelle != Color.BLACK) {
                    grille[i][j].setBackground(Color.WHITE);
                }
            }
        }

        for (Point p : chemin) {
            Color couleurActuelle = grille[p.x][p.y].getBackground();
            if (couleurActuelle != Color.GREEN && couleurActuelle != Color.RED) {
                grille[p.x][p.y].setBackground(Color.CYAN);
            }
        }
        setChanged();
        notifyObservers();
    }

    public void marquerCaseConsultee(Point p) {
        if (grille[p.x][p.y].getBackground() != Color.GREEN &&
                grille[p.x][p.y].getBackground() != Color.RED) {
            grille[p.x][p.y].setBackground(Color.YELLOW);
        }
        grille[p.x][p.y].repaint();
        setChanged();
        notifyObservers();
    }

    public void reinitialiserGrille() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                Color couleurActuelle = grille[i][j].getBackground();
                if (couleurActuelle != Color.GREEN && couleurActuelle != Color.RED && couleurActuelle != Color.BLACK) {
                    grille[i][j].setBackground(Color.WHITE);
                }
            }
        }
        setChanged();
        notifyObservers();
    }

    public void setTimeExecution(float time) {
        this.timeExecution = time;
        setChanged();
        notifyObservers();
    }

    public float getTimeExecution() {
        return timeExecution;
    }

    public void setGeneratedStates(int states) {
        this.generatedStates = states;
        setChanged();
        notifyObservers();
    }

    public int getGeneratedStates() {
        return generatedStates;
    }

    public void setPathFound(boolean found) {
        this.pathFound = found;
        setChanged();
        notifyObservers();
    }

    public boolean isPathFound() {
        return pathFound;
    }

    public void setPathLength(int length) {
        this.pathLength = length;
        setChanged();
        notifyObservers();
    }

    public int getPathLength() {
        return pathLength;
    }

    // Logique exécution des algorithmes
    public void doAlgo(JButton bouton) {
        if(bouton.getText().equals("Demarrer")){
            this.reinitialiserGrille();
             String algoSelectionne = this.getAlgorithmeSelectionne();
            IRecherche algo;

            switch (algoSelectionne) {
                case "Dijkstra":
                    algo = new Dijkstra(this);
                    List<Point> cheminDijkstra = algo.resoudre();
                    if (cheminDijkstra != null) {
                        this.setChemin(cheminDijkstra);
                        System.out.println("Algo Dijkstra réussi, chemin trouvé");
                    } else {
                        System.out.println("Algo Dijkstra terminé, pas de chemin trouvé");
                    }
                    break;
                case "A*":
                    algo = new AStar(this);
                    List<Point> cheminAStar = algo.resoudre();
                    if (cheminAStar != null) {
                        this.setChemin(cheminAStar);
                        System.out.println("Algo A* réussi, chemin trouvé");
                    } else {
                        System.out.println("Algo A* terminé, pas de chemin trouvé");
                    }
                    break;
                case "Largeur d'abord":
                    algo = new LargeurDabord(this);
                    List<Point> cheminLargeurDabord = algo.resoudre();
                    if (cheminLargeurDabord != null) {
                        this.setChemin(cheminLargeurDabord);
                        System.out.println("Algo Largeur d'abord réussi, chemin trouvé");
                    } else {
                        System.out.println("Algo Largeur d'abord terminé, pas de chemin trouvé");
                    }
                    break;
                case "Profondeur d'abord":
                    algo = new ProfondeurDabord(this);
                    List<Point> cheminProfondeurDabord = algo.resoudre();
                    if (cheminProfondeurDabord != null) {
                        this.setChemin(cheminProfondeurDabord);
                        System.out.println("Algo Profondeur d'abord réussi, chemin trouvé");
                    } else {
                        System.out.println("Algo Profondeur d'abord terminé, pas de chemin trouvé");
                    }
                    break;
                case "Greedy Best First":
                    algo = new GreedyBestFirst(this);
                    List<Point> cheminGreedyBestFirst = algo.resoudre();
                    if (cheminGreedyBestFirst != null) {
                        this.setChemin(cheminGreedyBestFirst);
                        System.out.println("Algo Greedy Best First réussi, chemin trouvé");
                    } else {
                        System.out.println("Algo Greedy Best First terminé, pas de chemin trouvé");
                    }
                    break;
                case "IDA*":
                    algo = new IDAStar(this);
                    List<Point> cheminIDAStar = algo.resoudre();
                    if (cheminIDAStar != null) {
                        this.setChemin(cheminIDAStar);
                        System.out.println("Algo IDA* réussi, chemin trouvé");
                    } else {
                        System.out.println("Algo IDA* terminé, pas de chemin trouvé");
                    }
                    break;
                default:
                    System.out.println("IRecherche non reconnu");
            }
        }
    }
}
