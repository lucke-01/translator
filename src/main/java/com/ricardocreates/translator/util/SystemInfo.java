package com.ricardocreates.translator.util;

/**
 * Utility class to know information about the runtime system.
 * @author ricardo
 *
 */
public class SystemInfo {
    private SystemInfo() {}
    
    /**
     * returns java runtime version
     * @return
     */
    public static String javaVersion() {
        return System.getProperty("java.version");
    }
    /**
     * return javafx runtime version
     * @return
     */
    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

}