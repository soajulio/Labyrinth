package Modeles;

import javax.swing.*;
import java.awt.*;
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
}
