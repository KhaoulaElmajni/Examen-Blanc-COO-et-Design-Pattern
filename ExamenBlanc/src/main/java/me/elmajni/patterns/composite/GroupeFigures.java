package me.elmajni.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class GroupeFigures extends Figure{
    private List<Figure> figures=new ArrayList<>();

    public Figure ajouterFigure(Figure figure){
        figure.niveau=this.niveau+1;
        figures.add(figure);
        return figure;
    }


    @Override
    public double calculSurface() {
        double surfaceTotal = 0.0;

        for (Figure f : figures) {
            surfaceTotal+=f.calculSurface();
        }

        return surfaceTotal;
    }

    @Override
    public double calculPerimetre() {
        double perimetreTotal = 0.0;

        for (Figure f : figures) {
            perimetreTotal+=f.calculPerimetre();
        }

        return perimetreTotal;
    }

    @Override
    public void afficher() {
        System.out.println(tabs()+"Affichage du Groupe : ==> {");
        for (Figure figure :figures){
            figure.afficher();
        }
        System.out.println("}");
    }

    public List<Figure> getFigures() {
        return figures;
    }
}
