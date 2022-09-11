package com.jdc.game.jgame.sprites;

import com.jdc.game.jgame.core.Sprite;

import java.awt.*;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class Apple extends Sprite {

    private final SecureRandom random;
    private final List<Integer> xPoints;
    private final List<Integer> yPoints;

    public Apple() {
        this.random = new SecureRandom();
        this.xPoints = new LinkedList<>();
        this.yPoints = new LinkedList<>();
        this.setWidth(10);
        this.setHeight(10);
    }

    @Override
    public void reset() {
        this.updatePositions();
    }

    @Override
    public void draw(Graphics2D g2) { }

    @Override
    public void update(Graphics2D g2) {
        if (this.xPoints.size() == 0) this.fillXPoints();
        if (this.yPoints.size() == 0) this.fillYPoints();

        if (this.getX() == 0 && this.getY() == 0) {
            this.updatePositions();
        }

        g2.setColor(Color.RED);
        g2.fillRect(this.getX(), this.getY(), 10, 10);
    }

    @Override
    public void collision(Graphics2D g2) {
        this.updatePositions();
    }

    private void fillXPoints() {
        for (int x = 0; x < this.getParent().getSpace().getWidth(); x += 10) {
            this.xPoints.add(x);
        }
    }

    private void fillYPoints() {
        for (int y = 0; y < this.getParent().getSpace().getHeight(); y += 10) {
            this.yPoints.add(y);
        }
    }

    private void updatePositions() {
        this.setX(this.xPoints.get(this.random.nextInt(this.xPoints.size())));
        this.setY(this.yPoints.get(this.random.nextInt(this.yPoints.size())));
    }

}
