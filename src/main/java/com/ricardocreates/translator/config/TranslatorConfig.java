package com.ricardocreates.translator.config;

import java.io.File;
import java.util.List;

import com.ricardocreates.translator.model.KeyValuePair;

public class TranslatorConfig {
    public static final String CONFIG_FILE_NAME = "./config/config.properties";
    public static final String MICROSOFT_API_KEY = "microsoft";
    public static final KeyValuePair<String, String> MICROSOFT_API = new KeyValuePair<>("microsoft", "Microsoft");
    public static final List<KeyValuePair<String, String>> AVAILABLE_APIS = List.of(MICROSOFT_API);
    
    public static final KeyValuePair<String, String> DEFAULT_API = MICROSOFT_API;
    public static final String DEFAULT_SOURCE_LANGUAGE_ALFA2 = "en";
    public static final String DEFAULT_TARGET_LANGUAGE_ALFA2 = "es";
    
    private static UserConfig userConfig;
    
    public static UserConfig getUserConfig() {
        if (userConfig == null) {
            userConfig = UserConfig.of(new File(CONFIG_FILE_NAME));
        }
        return userConfig;
        
    }
}
