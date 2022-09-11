package com.jdc.game.jgame.core;

import com.jdc.game.jgame.core.paint.Drawable;
import com.jdc.game.jgame.core.paint.PlotterAdapter;

import java.awt.*;
import java.util.List;

public class SpriteManager extends PlotterAdapter {

    private final List<Sprite> sprites;
    private Dimension space;

    public SpriteManager(List<Sprite> sprites) {
        this.sprites = sprites;
        this.sprites.forEach(sprite -> sprite.setParent(this));
    }

    public void toUp() {
        this.sprites.forEach(Sprite::toUp);
    }

    public void toDown() {
        this.sprites.forEach(Sprite::toDown);
    }

    public void toLeft() {
        this.sprites.forEach(Sprite::toLeft);
    }

    public void toRight() {
        this.sprites.forEach(Sprite::toRight);
    }

    @Override
    public void reset() {
        super.reset();
        this.sprites.forEach(Drawable::reset);
    }

    @Override
    public void update(Graphics2D g2) {
        this.sprites.forEach(sprite -> {
            sprite.setArea(this.getSpace());
            sprite.update(g2);
        });
    }

    public Dimension getSpace() {
        return space;
    }

    public void setSpace(Dimension space) {
        this.space = space;
    }

    public List<Sprite> getSprites() {
        return this.sprites;
    }

}
