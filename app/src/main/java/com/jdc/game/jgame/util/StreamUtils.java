package com.jdc.game.jgame.util;

import org.tinylog.Logger;

import java.io.Closeable;

public class StreamUtils {

    public static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception ex) {
                Logger.error(ex);
            }
        }
    }

}
