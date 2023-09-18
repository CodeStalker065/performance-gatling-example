package com.example.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Environment {

    public static Config config = ConfigFactory.load();
    public static String BASE_URL = getProperty("base_url");
    public static String EXAMPLE_URL = getProperty("example_url");

    private static String getProperty(String name) {
        try {
            return config.getString(name);
        } catch (Exception e) {
            // if some property does not exist, return null
            return null;
        }
    }
}
