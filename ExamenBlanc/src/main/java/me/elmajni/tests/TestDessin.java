package me.elmajni.tests;


import me.elmajni.dessin.Dessin;
import me.elmajni.dessin.Point;
import me.elmajni.designpatterns.composite.Cercle;
import me.elmajni.designpatterns.composite.GroupeFigures;
import me.elmajni.designpatterns.composite.Rectangle;

public class TestDessin {
    public static void main(String[] args) throws Exception {

        Dessin dessin1=new Dessin();

        dessin1.ajouterFigure(new Cercle(new Point(14,3),35));
        dessin1.ajouterFigure(new Rectangle(new Point(50,10),87,200));
        GroupeFigures groupeFigures = (GroupeFigures) dessin1.ajouterFigure(new GroupeFigures());

        groupeFigures.ajouterFigure(new Rectangle(new Point(30,30),10,120));
        groupeFigures.ajouterFigure(new Cercle(new Point(50,60),30));

        dessin1.afficher();
        dessin1.serialiser("dessin.data");


        Dessin dessin2=new Dessin();

        dessin2.ajouterFigure(new Cercle(new Point(14,3),35));
        dessin2.ajouterFigure(new Rectangle(new Point(50,10),87,200));
        GroupeFigures groupeFigures1 = (GroupeFigures) dessin2.ajouterFigure(new GroupeFigures());

        groupeFigures1.ajouterFigure(new Rectangle(new Point(30,30),10,120));
        groupeFigures1.ajouterFigure(new Cercle(new Point(50,60),30));

        dessin2.afficher();
        dessin2.serialiser("dessin.data");
    }
}
