package Modeles;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Labyrinthe extends Observable {

    public enum EtatSelection { DEPART, ARRIVEE, MUR, VIDE, DEMARRER }
    private EtatSelection etatActuel = EtatSelection.VIDE;
    public boolean departClicked = false;

    public boolean isDepartClicked() {
        return departClicked;
    }

    public void setDepartClicked(boolean departClicked) {
        this.departClicked = departClicked;
        setChanged();
        notifyObservers();
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
}
