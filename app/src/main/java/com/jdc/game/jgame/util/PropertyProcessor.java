package com.jdc.game.jgame.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class PropertyProcessor {

    public static Map<String, String> readProperties(String url) {
        Map<String, String> properties = new LinkedHashMap<>();
        ResourceUtils.getInstance().readAllLines(url)
                .parallelStream()
                .filter(line -> !line.isEmpty() && !line.equals("\n") && !line.startsWith("#"))
                .map(line -> line.replace("\n", ""))
                .forEach(line -> {
                    String[] chunks = line.split("=");
                    if (chunks.length > 1) {
                        StringBuilder rest = new StringBuilder();
                        for (int i = 1; i < chunks.length; i++) {
                            rest.append(chunks[i]).append(" ");
                        }
                        properties.put(chunks[0], rest.toString().trim());
                    } else {
                        properties.put(chunks[0], "");
                    }
                });
        return properties;
    }

}
