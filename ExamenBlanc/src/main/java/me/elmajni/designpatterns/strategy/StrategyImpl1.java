package me.elmajni.designpatterns.strategy;


import me.elmajni.designpatterns.composite.Cercle;
import me.elmajni.designpatterns.composite.Figure;
import me.elmajni.designpatterns.composite.GroupeFigures;
import me.elmajni.designpatterns.composite.Rectangle;

import java.util.List;

public class StrategyImpl1 implements IStrategy {

    @Override
    public void traiter(List<Figure> figures) {
        for(Figure figure:figures){
            System.out.println("Le traitement 1");
            figure.setCouleurContour(15);
            figure.setCouleurRemplissage(35);
            figure.setEpaisseurContour(4);

            if(figure instanceof Cercle){

            }

            if(figure instanceof Rectangle){

            }

                if(figure instanceof GroupeFigures){
                traiter(((GroupeFigures)figure).getFigures());
            }
            figure.afficher();
        }
    }
}
