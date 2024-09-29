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
    private boolean demarrerClicked = false;
    private boolean arriveeClicked = false;
    private boolean murClicked = false;
    private boolean videClicked = false;

    private JButton buttonTempDepart;
    private JButton buttonTempArrivee;

    private JButton[][] grille;

    public static int counter = 0;
    private final int id;

    private String algorithmeSelectionne;

    private float timeExecution;
    private int generatedStates;
    private boolean pathFound;
    private int pathLength;

    public Labyrinthe() {
        this.id = counter++;
    }

    public int getId() {
        return id;
    }

    public boolean isDepartClicked() {
        return departClicked;
    }

    public void setDepartClicked(boolean departClicked) {
        this.departClicked = departClicked;
        setChanged();
        notifyObservers();
    }

    public boolean isDemarrerClicked() {
        return demarrerClicked;
    }

    public void setDemarrerClicked(boolean demarrerClicked) {
        this.demarrerClicked = demarrerClicked;
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

    // Méthode pour mettre à jour l'état
    public void setEtatActuel(EtatSelection etat) {
        this.etatActuel = etat;
        setChanged();
        notifyObservers(); // Si vous utilisez le pattern Observer
    }

    // Getter pour l'état actuel
    public EtatSelection getEtatActuel() {
        return etatActuel;
    }

    // Logique pour Ecouteurboutons
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

    // Méthode pour initialiser la grille
    public void setGrille(JButton[][] grille) {
        this.grille = grille;
        System.out.println("Grille initialisée avec succès !");
    }

    // Méthode retournant la grille
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

    public void setChemin(List<Point> chemin) {
        // Ne pas effacer les cases jaunes, vertes, rouges ou noires
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                Color couleurActuelle = grille[i][j].getBackground();
                if (couleurActuelle != Color.YELLOW && couleurActuelle != Color.GREEN &&
                        couleurActuelle != Color.RED && couleurActuelle != Color.BLACK) {
                    grille[i][j].setBackground(Color.WHITE);
                }
            }
        }

        // Mettez à jour la grille avec le nouveau chemin
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
}
