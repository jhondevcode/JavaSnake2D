/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.jdc.game.jgame;

import com.jdc.game.jgame.core.Game;
import com.jdc.game.jgame.core.GameCanvas;

public class App {

    public static void main(String[] args) {
        try {
            Game.getInstance().getGameWindow().addCanvas(new GameCanvas());
            Game.getInstance().getGameWindow().loop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
