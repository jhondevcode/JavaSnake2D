package com.jdc.game.jgame.config;

import java.awt.*;

public class WindowConfig {

    private String windowTitle;
    private int windowWidth;
    private int windowHeight;
    private int windowPosition;
    private Color windowColor;

    public WindowConfig() {}

    public void addWindowTitle(String title) {
        this.windowTitle = title;
    }

    public String getWindowTitle() {
        return this.windowTitle;
    }

    public void addWindowWidth(int width) {
        this.windowWidth = width;
    }

    public int getWindowWidth() {
        return this.windowWidth;
    }

    public void addWindowHeight(int height) {
        this.windowHeight = height;
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    public void addWindowPosition(int position) {
        this.windowPosition = position;
    }

    public int getWindowPosition() {
        return this.windowPosition;
    }

    public Color getWindowColor() {
        return windowColor;
    }

    public void addWindowColor(Color windowColor) {
        this.windowColor = windowColor;
    }
}
