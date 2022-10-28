package me.elmajni.dessin;

import me.elmajni.designpatterns.composite.Figure;
import me.elmajni.designpatterns.strategy.IStrategy;
import me.elmajni.designpatterns.strategy.StrategyImpl1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dessin implements Serializable {
    private List<Figure> figures=new ArrayList<>();
    private transient IStrategy strategy=new StrategyImpl1();

    public Figure ajouterFigure(Figure figure){
        figures.add(figure);
        return figure;
    }

    public void supprimerFigure(Figure figure){
        figures.remove(figure);
    }

    public void serialiser(String fileName) throws Exception {
        File file=new File(fileName);
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }
    public void effectuerStrategyTraitement(){
        this.strategy.traiter(figures);
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void afficher(){
        for (Figure figure:figures){
            figure.afficher();
        }
    }

    public static Dessin desserialiser(String fileName) throws Exception {
        File file=new File(fileName);
        FileInputStream fileInputStream=new FileInputStream(file);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        Dessin dessin=(Dessin) objectInputStream.readObject();
        objectInputStream.close();
        return dessin;
    }
}
