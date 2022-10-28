package me.elmajni.tests;


import me.elmajni.dessin.Dessin;
import me.elmajni.designpatterns.strategy.StrategyImpl1;
import me.elmajni.designpatterns.strategy.StrategyImpl2;

public class TestSerialisation {

    public static void main(String[] args) throws Exception {
        Dessin dessin= Dessin.desserialiser("dessin.data");
        dessin.afficher();
        dessin.setStrategy(new StrategyImpl1());
        dessin.effectuerStrategyTraitement();
        dessin.setStrategy(new StrategyImpl2());
        dessin.effectuerStrategyTraitement();
    }

}
