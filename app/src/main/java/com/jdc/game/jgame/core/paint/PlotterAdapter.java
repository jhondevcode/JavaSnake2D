package com.jdc.game.jgame.core.paint;

import java.awt.*;

public abstract class PlotterAdapter implements Drawable {

    @Override
    public void draw(Graphics2D g2) {}

    @Override
    public void update(Graphics2D g2) {}

    @Override
    public void collision(Graphics2D g2) {}

    @Override
    public void reset() {}
}
