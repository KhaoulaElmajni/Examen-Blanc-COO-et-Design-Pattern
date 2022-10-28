package me.elmajni.tests;


import me.elmajni.dessin.Point;
import me.elmajni.designpatterns.composite.Cercle;
import me.elmajni.designpatterns.composite.Figure;
import me.elmajni.designpatterns.composite.GroupeFigures;
import me.elmajni.designpatterns.composite.Rectangle;
import me.elmajni.designpatterns.observer.Parametrage;

public class TestGroupeFigures {
    public static void main(String[] args) {

        System.out.println("_______________________________TEST1_______________________________");
        Figure figure1=new Cercle(new Point(2,3),12.5);
        System.out.println("le périmétre de la figure 1 = "+figure1.calculPerimetre());
        System.out.println("la surface de la figure 1 = "+figure1.calculSurface());
        figure1.afficher();

        System.out.println("====================================================================");
        Figure figure2=new Rectangle(new Point(5,5),12,56);
        System.out.println(figure2.calculPerimetre());
        System.out.println(figure2.calculSurface());
        figure2.afficher();

        System.out.println("====================================================================");
        Parametrage parametrageObservale=new Parametrage();
        parametrageObservale.addObserver(figure1);
        parametrageObservale.addObserver(figure2);
        parametrageObservale.setCouleurContour(900);

        figure1.afficher();
        figure2.afficher();

        System.out.println("=============Traitement de Groupe==========");
        GroupeFigures groupeFigures=new GroupeFigures();
        Figure f = groupeFigures.ajouterFigure(figure1);
        Figure f2 = groupeFigures.ajouterFigure(new Cercle(new Point(4,7),9.5));
        Figure f3 = groupeFigures.ajouterFigure(new Rectangle(new Point(5,5),12,56));
        Figure f4 = groupeFigures.ajouterFigure(new GroupeFigures().ajouterFigure(figure1));

        groupeFigures.ajouterFigure(new GroupeFigures().ajouterFigure(figure2));
        groupeFigures.afficher();

    }
}
