package me.elmajni.patterns.composite;



import me.elmajni.patterns.observer.Observer;
import me.elmajni.patterns.observer.Parametrage;

import java.io.Serializable;

public abstract class Figure implements Serializable, Observer
{
    protected int epaisseurContour;
    protected int couleurContour;
    protected int couleurRemplissage;
    protected int niveau;
    public abstract double calculSurface();
    public abstract double calculPerimetre();
    public abstract void afficher();

    @Override
    public String toString() {
        return
                "epaisseurContour = " + epaisseurContour +
                ", couleurContour = " + couleurContour +
                ", couleurRemplissage = " + couleurRemplissage ;
    }

    public int getEpaisseurContour() {
        return epaisseurContour;
    }

    public void setEpaisseurContour(int epaisseurContour) {
        this.epaisseurContour = epaisseurContour;
    }

    public int getCouleurContour() {
        return couleurContour;
    }

    public void setCouleurContour(int couleurContour) {
        this.couleurContour = couleurContour;
    }

    public int getCouleurRemplissage() {
        return couleurRemplissage;
    }

    public void setCouleurRemplissage(int couleurRemplissage) {
        this.couleurRemplissage = couleurRemplissage;
    }

    @Override
    public void update(Parametrage parametrage) {
        this.epaisseurContour =parametrage.getEpaisseurContour();
        this.couleurContour=parametrage.getCouleurContour();
        this.couleurRemplissage=parametrage.getCouleurRemplissage();
    }
    protected String tabs(){
        String tabs="";
        for (int i = 0; i <niveau ; i++) {
            tabs+="\t";
        }
        return tabs;
    }
}
