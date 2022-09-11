package com.jdc.game.jgame.util;

import org.tinylog.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class ResourceUtils {

    private static ResourceUtils instance;

    private ResourceUtils() {}

    public URL loadLocalFile(String location) {
        try {
            return this.loadWithClassStream(location);
        } catch (IOException ex0) {
            Logger.error(ex0.getMessage());
            try {
                return this.loadWithClassLoader(location);
            } catch (IOException ex1) {
                Logger.error(ex1.getMessage());
                try {
                    return this.loadWithSystemClassLoader(location);
                } catch (IOException ex2) {
                    Logger.error(ex2.getMessage());
                    try {
                        return this.loadWithThreadContext(location);
                    } catch (IOException ex3) {
                        Logger.error(ex3.getMessage());
                        return null;
                    }
                }
            }
        }
    }

    public File loadDiskFile(String path) {
        return new File(path);
    }

    private URL loadWithClassStream(String location) throws IOException {
        Logger.info("Trying load with class stream");
        URL resourceURL = ResourceUtils.class.getResource(location);
        if (resourceURL != null) {
            return resourceURL;
        } else {
            throw new IOException("Cannot load file with class stream");
        }
    }

    private URL loadWithClassLoader(String location) throws IOException {
        Logger.info("Trying load with class loader");
        URL resourceURL = ResourceUtils.class.getClassLoader().getResource(location);
        if (resourceURL != null) {
            return resourceURL;
        } else {
            throw new IOException("Cannot load file with class loader");
        }
    }

    private URL loadWithSystemClassLoader(String location) throws IOException {
        Logger.info("Trying load with system class loader");
        URL resourceURL = ClassLoader.getSystemClassLoader().getResource(location);
        if (resourceURL != null) {
            return resourceURL;
        } else {
            throw new IOException("Cannot load file with system class loader");
        }
    }

    private URL loadWithThreadContext(String location) throws IOException {
        Logger.info("Trying load with thread context");
        URL resourceURL = Thread.currentThread().getContextClassLoader().getResource(location);
        if (resourceURL != null) {
            return resourceURL;
        } else {
            throw new IOException("Cannot load file with thread context");
        }
    }

    public Path getFileStreamPath(URL url) {
        try {
            return Path.of(url.toURI());
        } catch (Exception ex) {
            Logger.error(ex);
            return null;
        }
    }

    public List<String> readAllLines(String path) {
        List<String> lines = new LinkedList<>();
        URL input = this.loadLocalFile(path);
        if (input != null) {
            InputStream inputStream = null;
            BufferedInputStream bis = null;
            try {
                inputStream = input.openStream();
                bis = new BufferedInputStream(inputStream);
                int code;
                StringBuilder builder = new StringBuilder();
                while ((code = bis.read()) != -1) {
                    if (((char) code) == '\n') {
                        lines.add(builder.toString());
                        builder.setLength(0);
                    } else {
                        builder.append((char) code);
                    }
                }
            } catch (Exception ex) {
                Logger.error(ex);
            } finally {
                StreamUtils.closeStream(inputStream);
                StreamUtils.closeStream(bis);
            }
        }
        return lines;
    }

    public static ResourceUtils getInstance() {
        return (instance == null ? (instance = new ResourceUtils()) : instance);
    }

}
