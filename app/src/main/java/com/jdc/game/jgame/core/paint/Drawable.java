package com.jdc.game.jgame.core.paint;

import java.awt.*;

public interface Drawable {

    void reset();

    void draw(Graphics2D g2);

    void update(Graphics2D g2);

    void collision(Graphics2D g2);

    void toUp();

    void toDown();

    void toLeft();

    void toRight();

}
