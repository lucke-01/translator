package com.ricardocreates.translator.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {
    public static Map<String, Object> propertiesToMap(File file) {
        Map<String, Object> propertiesMap = new HashMap<>();
        Properties prop = new Properties();
        FileReader fileReader;
        try {
            
            fileReader = new FileReader(file.getAbsolutePath());
            prop.load(fileReader);
            
            Set<String> propKeys = prop.stringPropertyNames();
            
            propKeys.stream().peek(key -> propertiesMap.put(key, prop.getProperty(key))).close();
            
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        
        
        return propertiesMap;
    }
}
