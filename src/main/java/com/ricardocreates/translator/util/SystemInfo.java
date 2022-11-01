package com.ricardocreates.translator.util;

/**
 * Utility class to know information about the runtime system.
 */
public class SystemInfo {
    private SystemInfo() {
    }

    /**
     * returns java runtime version
     *
     * @return java version
     */
    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    /**
     * return javafx runtime version
     *
     * @return javafx version
     */
    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

}