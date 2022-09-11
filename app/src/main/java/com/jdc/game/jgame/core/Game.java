package com.jdc.game.jgame.core;

import com.jdc.game.jgame.components.GameWindow;
import com.jdc.game.jgame.config.WindowConfig;
import com.jdc.game.jgame.util.PropertyProcessor;
import org.tinylog.Logger;

import java.util.Map;

public class Game {

    // global instance
    private static Game localInstance = null;
    private static Map<String, String> plainSettings = null;

    private GameWindow gameWindow;

    public Game() {}
    
    public void configGameWindow(WindowConfig config) {
        if (this.gameWindow == null) {
            this.gameWindow = new GameWindow();
        }
        if (config == null) {
            GameConfigurator.getInstance().configureWindow(this.gameWindow);
        }
    }

    public GameWindow getGameWindow() {
        if (this.gameWindow == null) {
            this.configGameWindow(null);
        }
        return this.gameWindow;
    }

    public static Game getInstance() {
        if (localInstance == null) {
            localInstance = new Game();
        }
        return localInstance;
    }

    public static Map<String, String> getPlainSettings() {
        if (plainSettings == null) {
            try {
                plainSettings = PropertyProcessor.readProperties("settings.properties");
            } catch (Exception ex) {
                Logger.error(ex);
                plainSettings = Map.of();
            }
        }

        return plainSettings;
    }

}