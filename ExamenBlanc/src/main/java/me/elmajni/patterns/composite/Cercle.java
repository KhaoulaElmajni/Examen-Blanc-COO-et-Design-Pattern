package me.elmajni.patterns.composite;

import me.elmajni.dessin.Point;

public class Cercle extends Figure {
    private Point centre;
    private double rayon;

    public Cercle(Point centre, double rayon) {
        this.centre = centre;
        this.rayon = rayon;
    }

    @Override
    public double calculSurface() {
        return Math.PI*rayon*rayon;
    }

    @Override
    public double calculPerimetre() {
        return 2*Math.PI*rayon;
    }

    @Override
    public void afficher() {
        System.out.println(tabs()+"Cercle ==> ( X = "
                +centre.getX()+", Y = "+centre.getY()+", Rayon = "+rayon+","+super.toString()+")");
    }
}
