package Modeles;

import java.awt.*;

public class Case {
    private int x;
    private int y;
    private boolean mur;
    private boolean depart;
    private boolean arrivee;
    private boolean vide;
    private final Color erreur =  new Color(227, 29, 29);
    private final Color vert = new Color(60, 162, 35);
    private final Color blanc = new Color(255, 255, 255);
    private final Color noir = new Color(0, 0, 0);

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.mur = false;
        this.depart = false;
        this.arrivee = false;
        this.vide = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color setGreen() {
        return vert;
    }

    public Boolean isMur() {
        return mur;
    }

    public Boolean isDepart() {
        return depart;
    }

    public Boolean isArrivee() {
        return arrivee;
    }

    public Boolean isVide() {
        return vide;
    }
}
