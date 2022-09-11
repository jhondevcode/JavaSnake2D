package com.jdc.game.jgame.core;

import com.jdc.game.jgame.components.GameWindow;
import com.jdc.game.jgame.sprites.Apple;
import com.jdc.game.jgame.sprites.Snake;
import com.jdc.game.jgame.util.ValueConverter;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class GameCanvas extends JPanel implements KeyListener, Runnable {

    private final SpriteManager spriteManager;
    private boolean adjust = false;

    public GameCanvas() {
        super(null, true);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.spriteManager = new SpriteManager(Arrays.asList(new Apple(), new Snake()));
        this.spriteManager.toRight();
        new Thread(this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.getToolkit().sync();
        Graphics2D g2 = (Graphics2D) g;
        this.addGrids(g2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.getToolkit().sync();
        Graphics2D g2 = (Graphics2D) g;
        this.spriteManager.setSpace(this.getSize());
        this.spriteManager.update(g2);
    }

    private void addGrids(Graphics2D g) {
        if (!this.adjust) {
            GameWindow screen = Game.getInstance().getGameWindow();
            int paneHeight = screen.getWindowConfig().getWindowHeight() - this.getHeight();
            screen.setSize(screen.getWidth(), screen.getWindowConfig().getWindowHeight() + paneHeight);
            this.adjust = true;
        }
        // Color c = new Color(20, 20, 20);
        g.setColor(Color.decode("0x141414"));
        for (int i = 10; i <= this.getWidth(); i+=10) {
            g.drawLine(0, i, this.getWidth(), i);
            g.drawLine(i, 0, i, this.getHeight());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38 -> this.spriteManager.toUp();
            case 40 -> this.spriteManager.toDown();
            case 37-> this.spriteManager.toLeft();
            case 39 -> this.spriteManager.toRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void run() {
        while (true) {
            synchronized(this.spriteManager) {
                this.repaint();
            }
            try {
                Thread.sleep(Integer.parseInt(ValueConverter.secureString(Game.getPlainSettings().get("game.engine.interval"), "100")));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
