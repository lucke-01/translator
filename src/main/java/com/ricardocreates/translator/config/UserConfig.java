package com.ricardocreates.translator.config;

import com.ricardocreates.translator.util.PropertiesUtil;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.Map;

@Data
@Builder
/**
 * representation of user config file
 */
public class UserConfig {
    public static final String DEFAULT_API_KEY_PROPERTY = "config.defaultApi";
    public static final String DEFAULT_SOURCE_LANGUAGE_PROPERTY = "config.defaultSourceLanguage";
    public static final String DEFAULT_TARGET_LANGUAGE_PROPERTY = "config.defaultTargetLanguage";
    public static final String MICROSOFT_API_SUBCRIPTION_KEY_PROPERTY = "config.api.microsoft.ocpApimSubscriptionKey";
    public static final String MICROSOFT_API_SUBCRIPTION_REGION_PROPERTY = "config.api.microsoft.ocpApimSubscriptionRegion";

    public static final String DELAYED_THREAD_DESIRED_DURATION_BETWEEN_LAST_KEY_PRESSED_PROPERTY = "delayThread.desiredDurationBetweenLastKeyPressed";
    public static final String DELAYED_THREAD_MILLISECONDS_LOOP_PROPERTY = "delayThread.millisecondsLoop";

    /**
     * time to wait
     */
    public static final long DELAYED_THREAD_MILLISECONDS_LOOP = 100;
    /**
     * desired time to wait until the last key press
     */
    private static final long DELAYED_THREAD_DESIRED_DURATION_BETWEEN_LAST_KEY_PRESSED = 700;

    private String defaultApi;
    private String defaultSourceLanguage;
    private String defaultTargetLanguage;
    private MicrosoftApiConfig microsoftApiConfig;
    private DelayedThreadConfig delayedThread;

    /**
     * creates a new instance of userConfig using config.json file or default config if no exists
     *
     * @param configFile - config file
     * @return a UserConfig
     */
    public static UserConfig of(File configFile) {
        Map<String, Object> properties = PropertiesUtil.propertiesToMap(configFile);

        return UserConfig.builder()
                .defaultApi(properties.getOrDefault(DEFAULT_API_KEY_PROPERTY, TranslatorConfig.DEFAULT_API).toString())
                .defaultSourceLanguage(properties.getOrDefault(DEFAULT_SOURCE_LANGUAGE_PROPERTY, TranslatorConfig.DEFAULT_SOURCE_LANGUAGE_ALFA2).toString())
                .defaultTargetLanguage(properties.getOrDefault(DEFAULT_TARGET_LANGUAGE_PROPERTY, TranslatorConfig.DEFAULT_TARGET_LANGUAGE_ALFA2).toString())
                .delayedThread(
                        DelayedThreadConfig.builder()
                                .desiredDurationBetweenLastKeyPressed(
                                        Long.parseLong(properties.getOrDefault(DELAYED_THREAD_DESIRED_DURATION_BETWEEN_LAST_KEY_PRESSED_PROPERTY, DELAYED_THREAD_DESIRED_DURATION_BETWEEN_LAST_KEY_PRESSED).toString())
                                )
                                .millisecondsLoop(
                                        Long.parseLong(properties.getOrDefault(DELAYED_THREAD_MILLISECONDS_LOOP_PROPERTY, DELAYED_THREAD_MILLISECONDS_LOOP).toString())
                                )
                                .build()
                )
                .microsoftApiConfig(
                        MicrosoftApiConfig.builder()
                                .ocpApimSubscriptionKey(properties.get(MICROSOFT_API_SUBCRIPTION_KEY_PROPERTY).toString())
                                .ocpApimSubscriptionRegion(properties.get(MICROSOFT_API_SUBCRIPTION_REGION_PROPERTY).toString())
                                .build()
                )
                .build();
    }
}
