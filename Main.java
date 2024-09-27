import Controllers.boutons.EcouteurBoutons;
import Controllers.grille.EcouteurGrille;
import Modeles.Labyrinthe;
import Vues.VueFenetre;

public class Main {

    public static void main(String[] args) {
        Labyrinthe labyrinthe = new Labyrinthe();

        EcouteurBoutons ecouteurBoutons = new EcouteurBoutons(labyrinthe);

        EcouteurGrille ecouteurGrille = new EcouteurGrille(labyrinthe);

        VueFenetre vueFenetre = new VueFenetre(labyrinthe, ecouteurBoutons, ecouteurGrille);
    }
}
