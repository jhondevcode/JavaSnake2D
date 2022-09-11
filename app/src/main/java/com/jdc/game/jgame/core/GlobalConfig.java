package com.jdc.game.jgame.core;

abstract class GlobalConfig {

    protected String replaceNull(String val1, String val2) {
        return val1 != null ? val1 : val2;
    }

}
