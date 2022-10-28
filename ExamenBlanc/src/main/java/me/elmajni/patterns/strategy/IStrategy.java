package me.elmajni.patterns.strategy;


import me.elmajni.patterns.composite.Figure;

import java.util.List;

public interface IStrategy {
    public void traiter(List<Figure> figures);
}
