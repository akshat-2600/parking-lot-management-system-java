package com.parkinglot.utils;

import java.io.IOException;
import java.util.logging.*;

public final class LoggerConfig {
    private LoggerConfig(){}

    public static void configure() {
        Logger root = Logger.getLogger("");
        Handler fileHandler;
        try {
            fileHandler = new FileHandler("logs/parking-%u-%g.log", 1024 * 1024, 5, true);
            fileHandler.setFormatter(new SimpleFormatter());
            root.addHandler(fileHandler);
        } catch (IOException e) {
            root.warning("Failed to initialize file logger: " + e.getMessage());
        }

        // set default level
        root.setLevel(Level.INFO);
    }
}