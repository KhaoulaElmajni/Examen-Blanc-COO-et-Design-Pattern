package me.elmajni.tests;


import me.elmajni.dessin.Dessin;
import me.elmajni.dessin.Point;
import me.elmajni.designpatterns.composite.Cercle;
import me.elmajni.designpatterns.composite.GroupeFigures;
import me.elmajni.designpatterns.composite.Rectangle;

public class TestDessin {
    public static void main(String[] args) throws Exception {

        Dessin dessin=new Dessin();

        dessin.ajouterFigure(new Cercle(new Point(14,3),35));
        dessin.ajouterFigure(new Cercle(new Point(6,12),27));
        dessin.ajouterFigure(new Rectangle(new Point(50,10),87,200));
        GroupeFigures g1 = (GroupeFigures) dessin.ajouterFigure(new GroupeFigures());
        g1.ajouterFigure(new Rectangle(
                new Point(30,30),10,120));
        g1.ajouterFigure(new Cercle(new Point(50,60),30));
        dessin.afficher();
        dessin.serialiser("dessin1.data");
    }
}
