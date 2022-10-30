package com.ricardocreates.translator.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Utily class to work with properties files
 * @author ricardo
 *
 */
public class PropertiesUtil {
    private PropertiesUtil() {}
    
    /**
     * given a property file return a HashMap which will contain the properties 
     * @param file
     * @return
     */
    public static Map<String, Object> propertiesToMap(File file) {
        if (file == null) {
            throw new IllegalArgumentException("not file to parse");
        }
        Map<String, Object> propertiesMap = new HashMap<>();
        Properties prop = new Properties();

        try (FileReader fileReader = new FileReader(file.getAbsolutePath())) {
            prop.load(fileReader);

            Set<String> propKeys = prop.stringPropertyNames();

            for (String propKey : propKeys) {
                propertiesMap.put(propKey, prop.getProperty(propKey));
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return propertiesMap;
    }
}
