package me.elmajni.patterns.strategy;


import me.elmajni.patterns.composite.Figure;
import me.elmajni.patterns.composite.GroupeFigures;

import java.util.List;

public class StrategyImpl2 implements IStrategy {
    @Override
    public void traiter(List<Figure> figures) {
        for(Figure figure:figures){
            System.out.println("Le traitement 2");
            figure.setCouleurContour(25);
            figure.setCouleurRemplissage(45);
            figure.setEpaisseurContour(10);

            if(figure instanceof GroupeFigures){
                traiter(((GroupeFigures)figure).getFigures());
            }
            figure.afficher();
        }
    }
}
