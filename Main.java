import Controllers.boutons.EcouteurBoutons;
import Modeles.Labyrinthe;
import Vues.VueFenetre;

public class Main {

    public static void main(String[] args) {
        Labyrinthe labyrinthe = new Labyrinthe();

        EcouteurBoutons ecouteurBoutons = new EcouteurBoutons(labyrinthe);

        VueFenetre vueFenetre = new VueFenetre(ecouteurBoutons);
    }
}
