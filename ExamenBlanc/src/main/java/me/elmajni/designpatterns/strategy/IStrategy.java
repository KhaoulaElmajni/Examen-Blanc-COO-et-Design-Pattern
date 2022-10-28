package me.elmajni.designpatterns.strategy;


import me.elmajni.designpatterns.composite.Figure;

import java.util.List;

public interface IStrategy {
    public void traiter(List<Figure> figures);
}
