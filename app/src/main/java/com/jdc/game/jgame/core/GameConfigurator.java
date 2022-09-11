package com.jdc.game.jgame.core;

import com.jdc.game.jgame.components.GameWindow;
import com.jdc.game.jgame.config.WindowConfig;
import com.jdc.game.jgame.common.Positions;

import java.awt.*;
import java.util.Map;

class GameConfigurator extends GlobalConfig {

    private static GameConfigurator configurator = null;

    GameConfigurator() {
    }

    public void configureWindow(GameWindow window) {
        WindowConfig config = new WindowConfig();
        Map<String, String> settings = Game.getPlainSettings();
        config.addWindowTitle(this.replaceNull(settings.get("game.window.title"), "Java Draw Engine"));
        config.addWindowWidth(Integer.parseInt(this.replaceNull(settings.get("game.window.width"), "800")));
        config.addWindowHeight(Integer.parseInt(this.replaceNull(settings.get("game.window.height"), "400")));
        config.addWindowPosition(Integer.parseInt(this.replaceNull(settings.get("game.window.position"), String.valueOf(Positions.CENTER))));
        config.addWindowColor(Color.decode(this.replaceNull(settings.get("game.window.background"), "0xffffff")));
        window.setWindowConfig(config);

        window.setSize(config.getWindowWidth(), config.getWindowHeight());
        window.setTitle(config.getWindowTitle());
        if (config.getWindowPosition() == Positions.CENTER) {
            window.setLocationRelativeTo(null);
        }
        if (!Boolean.parseBoolean(this.replaceNull(settings.get("game.window.resizable"), "true"))) {
            window.setResizable(false);
        }

        window.setDefaultCloseOperation(GameWindow.EXIT_ON_CLOSE);
    }

    static GameConfigurator getInstance() {
        if (configurator == null) {
            configurator = new GameConfigurator();
        }

        return configurator;
    }

}
