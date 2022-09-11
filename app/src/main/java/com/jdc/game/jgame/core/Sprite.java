package com.jdc.game.jgame.core;

import com.jdc.game.jgame.common.Positions;
import com.jdc.game.jgame.core.paint.Drawable;

import java.awt.*;

public abstract class Sprite implements Drawable {

    private int width;
    private int height;
    private int x;
    private int y;
    private int direction;
    private Dimension area;
    private SpriteManager parent;

    public Sprite() {}

    @Override
    public abstract void draw(Graphics2D g2);

    @Override
    public abstract void update(Graphics2D g2);

    @Override
    public abstract void collision(Graphics2D g2);

    @Override
    public abstract void reset();

    @Override
    public void toUp() {
        this.direction = Positions.UP;
    }

    @Override
    public void toDown() {
        this.direction = Positions.DOWN;
    }

    @Override
    public void toLeft() {
        this.direction = Positions.LEFT;
    }

    @Override
    public void toRight() {
        this.direction = Positions.RIGHT;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void moveUp(Graphics2D g2) {}

    public void moveDown(Graphics2D g2) {}

    public void moveLeft(Graphics2D g2) {}

    public void moveRight(Graphics2D g2) {}

    public Dimension getArea() {
        return area;
    }

    public void setArea(Dimension area) {
        this.area = area;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public SpriteManager getParent() {
        return parent;
    }

    public void setParent(SpriteManager parent) {
        this.parent = parent;
    }

}
