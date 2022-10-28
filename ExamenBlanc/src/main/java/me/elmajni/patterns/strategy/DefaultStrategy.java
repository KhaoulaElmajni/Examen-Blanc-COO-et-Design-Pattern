package me.elmajni.patterns.strategy;


import me.elmajni.patterns.composite.Figure;
import me.elmajni.patterns.composite.GroupeFigures;

import java.util.List;

public class DefaultStrategy implements IStrategy {
    @Override
    public void traiter(List<Figure> figures) {
        for(Figure figure:figures){
            System.out.println("Strategy par défaut");
            figure.setCouleurContour(0);
            figure.setCouleurRemplissage(0);
            figure.setEpaisseurContour(0);

            if(figure instanceof GroupeFigures){
                traiter(((GroupeFigures)figure).getFigures());
            }

            figure.afficher();
        }
    }
}
