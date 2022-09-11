package com.jdc.game.jgame.sprites;

import com.jdc.game.jgame.common.Positions;
import com.jdc.game.jgame.core.Game;
import com.jdc.game.jgame.core.Sprite;
import com.jdc.game.jgame.util.ValueConverter;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Snake extends Sprite {

    private final Color snakeBody;
    private int oldDirection;
    private final Rectangle oldPosition;
    private final List<Rectangle> chunks;

    public Snake() {
        this.setWidth(10);
        this.setHeight(10);
        this.setY(0);
        this.setX(0);
        this.snakeBody = Color.decode(ValueConverter.secureString(Game.getPlainSettings().get("game.snake.color"), "0x00FF00"));
        this.oldDirection = Positions.RIGHT;
        this.chunks = new LinkedList<>();
        this.oldPosition = new Rectangle(0, 0, 10, 10);
        this.chunks.add(new Rectangle(0, 0, 10, 10));
        this.chunks.add(new Rectangle(-10, 0, 10, 10));
    }


    @Override
    public void reset() {
        this.setWidth(10);
        this.setHeight(10);
        this.setY(0);
        this.setX(0);
        this.oldPosition.x = 0;
        this.oldPosition.y = 0;
        this.oldPosition.width = 10;
        this.oldPosition.height = 10;
        this.chunks.clear();
        this.chunks.add(new Rectangle(0, 0, 10, 10));
        this.chunks.add(new Rectangle(-10, 0, 10, 10));
        this.getParent().toRight();
    }

    @Override
    public void draw(Graphics2D g2) {}

    @Override
    public void update(Graphics2D g2) {
        switch (this.getDirection()) {
            case Positions.UP -> this.moveUp(g2);
            case Positions.DOWN -> this.moveDown(g2);
            case Positions.LEFT-> this.moveLeft(g2);
            case Positions.RIGHT -> this.moveRight(g2);
        }
    }

    @Override
    public void collision(Graphics2D g2) {
        this.getParent().getSprites().forEach(sprite -> {
            if (!sprite.equals(this)) {
                Rectangle spriteBounds = sprite.getBounds();
                if (spriteBounds.intersects(this.getBounds())) {
                    sprite.collision(g2);
                    this.increase(spriteBounds);
                }
            }
        });
    }

    @Override
    public void moveUp(Graphics2D g2) {
        g2.setColor(this.snakeBody);
        if (this.oldDirection != Positions.DOWN) {
            this.oldDirection = Positions.UP;
            this.oldPosition.x = this.getX();
            this.oldPosition.y = this.getY();
            if(this.getY() < 0) {
                this.setY(this.getArea().height + 10);
            }
            this.setY(this.getY() - 10);
            g2.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            this.collision(g2);
            this.paintQueue(g2);
            this.checkBody();
        } else {
            this.getParent().toDown();
            this.moveDown(g2);
        }

    }

    @Override
    public void moveDown(Graphics2D g2) {
        g2.setColor(this.snakeBody);
        if (this.oldDirection != Positions.UP) {
            this.oldDirection = Positions.DOWN;
            this.oldPosition.x = this.getX();
            this.oldPosition.y = this.getY();
            if (this.getY() > this.getArea().height) {
                this.setY(-10);
            }
            this.setY(this.getY() + 10);
            g2.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            this.collision(g2);
            this.paintQueue(g2);
            this.checkBody();
        } else  {
            this.getParent().toUp();
            this.moveUp(g2);
        }
    }

    @Override
    public void moveLeft(Graphics2D g2) {
        g2.setColor(this.snakeBody);
        if (this.oldDirection != Positions.RIGHT) {
            this.oldDirection = Positions.LEFT;
            this.oldPosition.x = this.getX();
            this.oldPosition.y = this.getY();
            if (this.getX() < 0) {
                this.setX(this.getArea().width + 10);
            }
            this.setX(this.getX() - 10);
            g2.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            this.collision(g2);
            this.paintQueue(g2);
            this.checkBody();
        } else {
            this.getParent().toRight();
            this.moveRight(g2);
        }

    }

    @Override
    public void moveRight(Graphics2D g2) {
        g2.setColor(this.snakeBody);
        if (this.oldDirection != Positions.LEFT) {
            this.oldDirection = Positions.RIGHT;
            this.oldPosition.x = this.getX();
            this.oldPosition.y = this.getY();
            if (this.getX() > this.getArea().width) {
                this.setX(-10);
            }
            this.setX(this.getX() + 10);
            g2.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            this.collision(g2);
            this.paintQueue(g2);
            this.checkBody();
        } else {
            this.getParent().toLeft();
            this.moveLeft(g2);
        }
    }

    private void increase(Rectangle position) {
        this.chunks.add(position);
    }

    private void paintQueue(Graphics2D g2) {
        if (this.chunks.size() > 0) {
            int oldX = 0, oldY = 0;
            for (int index = 0; index < this.chunks.size(); index++) {
                Rectangle chunk = this.chunks.get(index);
                if (index == 0) {
                    oldX = chunk.x;
                    oldY = chunk.y;
                    chunk.x = this.oldPosition.x;
                    chunk.y = this.oldPosition.y;
                } else {
                    int beforeX = chunk.x;
                    int beforeY = chunk.y;
                    chunk.x = oldX;
                    chunk.y = oldY;
                    oldX = beforeX;
                    oldY = beforeY;
                }
                this.chunks.set(index, chunk);
                g2.fill(chunk);
            }
        }
    }

    private void checkBody() {
        for (Rectangle sprite : this.chunks) {
            if (this.getBounds().intersects(sprite)) {
                this.getParent().reset();
                break;
            }
        }
    }

}
