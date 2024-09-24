package Modeles;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Labyrinthe extends Observable {

    public String typeMurSelect(JButton boutonSelect)
    {
        List<String> typeMur = new ArrayList<>() {{
            add("Mur");
            add("Depart");
            add("Arrivee");
            add("Vide");
            add("Demarrer");
        }};

        if(boutonSelect.getText().equals("Mur")){
            System.out.println(typeMur.get(0));
            return typeMur.get(0);
        }

        if(boutonSelect.getText().equals("Depart")){
            System.out.println(typeMur.get(1));
            return typeMur.get(1);
        }

        if(boutonSelect.getText().equals("Arrivee")){
            System.out.println(typeMur.get(2));
            return typeMur.get(2);
        }

        if(boutonSelect.getText().equals("Vide")){
            System.out.println(typeMur.get(3));
            return typeMur.get(3);
        }

        if(boutonSelect.getText().equals("Demarrer")){
            System.out.println(typeMur.get(4));
            return typeMur.get(4);
        }

        return null;
    }

}
