package com.jdc.game.jgame.components;

import javax.swing.*;

import com.jdc.game.jgame.config.WindowConfig;

public class GameWindow extends JFrame {

    private WindowConfig config; 
 
    public GameWindow() {
        super();
    }

    public void loop() {
        this.setVisible(true);
    }

    public WindowConfig getWindowConfig() {
        return this.config;
    }

    public void setWindowConfig(WindowConfig config) {
        this.config = config;
    }

    public void addCanvas(JPanel canvas) {
        this.setContentPane(canvas);
    }

}
