package me.elmajni.patterns.composite;

import me.elmajni.dessin.Point;

public class Rectangle extends Figure {
    private Point coinSupGauche;
    private double largeur;
    private double hauteur;

    public Rectangle(Point coinSupGauche, double largeur, double hauteur) {
        this.coinSupGauche = coinSupGauche;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    @Override
    public double calculSurface() {
        return largeur*hauteur;
    }

    @Override
    public double calculPerimetre() {
        return 2*(largeur+hauteur);
    }

    @Override
    public void afficher() {
        System.out.println(tabs()+"Rectangle==> (le coin supérieur gauche (X = "+ coinSupGauche.getX()+", Y = "+ coinSupGauche.getY()+"), largeur = "+largeur+
                ", hauteur = "+hauteur+","+super.toString()+")");
    }
}
