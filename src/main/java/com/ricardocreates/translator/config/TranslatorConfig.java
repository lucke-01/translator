package com.ricardocreates.translator.config;

import com.ricardocreates.translator.model.KeyValuePair;

import java.io.File;
import java.util.List;

/**
 * main app configuration
 */
public class TranslatorConfig {
    /**
     * path to user config file
     */
    public static final String CONFIG_FILE_NAME = "./build-app/config/configExample.properties";
    /**
     * libre translate key
     */
    public static final String LIBRE_TRANSLATE_API_KEY = "libreTranslate";
    /**
     * libre translate api
     */
    public static final KeyValuePair<String, String> LIBRE_TRANSLATE_API = new KeyValuePair<>(LIBRE_TRANSLATE_API_KEY, "Libre Translate");
    /**
     * libre translate key
     */
    public static final String CAMBRIDGE_API_KEY = "cambridge";
    /**
     * libre translate api
     */
    public static final KeyValuePair<String, String> CAMBRIDGE_API = new KeyValuePair<>(CAMBRIDGE_API_KEY, "Cambridge");
    /**
     * microsoft api
     */
    public static final String MICROSOFT_API_KEY = "microsoft";
    /**
     * microsoft api and api description
     */
    public static final KeyValuePair<String, String> MICROSOFT_API = new KeyValuePair<>(MICROSOFT_API_KEY, "Microsoft");
    /**
     * microsoft api
     */
    public static final String TEST_API_KEY = "test";
    /**
     * microsoft api and api description
     */
    public static final KeyValuePair<String, String> TEST_API = new KeyValuePair<>(TEST_API_KEY, "Test");
    /**
     * default api can be override by user configuration file
     */
    public static final KeyValuePair<String, String> DEFAULT_API = TEST_API;
    /**
     * List of available apis including description
     */
    public static final List<KeyValuePair<String, String>> AVAILABLE_APIS = List.of(
            MICROSOFT_API, TEST_API, LIBRE_TRANSLATE_API, CAMBRIDGE_API);
    /**
     * default source language can be override by user configuraion file
     */
    public static final String DEFAULT_SOURCE_LANGUAGE_ALFA2 = "en";
    /**
     * default target language can be override by user configuraion file
     */
    public static final String DEFAULT_TARGET_LANGUAGE_ALFA2 = "es";
    /**
     * user config singleton
     */
    private static UserConfig userConfig;

    /**
     * create a unique instance of userconfig
     *
     * @return a userConfig
     */
    public static UserConfig getUserConfig() {
        String configFilePath = System.getProperty("configFile", CONFIG_FILE_NAME);
        if (userConfig == null) {
            userConfig = UserConfig.of(new File(configFilePath));
        }
        return userConfig;

    }
}
