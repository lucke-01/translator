package com.ricardocreates.translator.config;

import java.io.File;
import java.util.Map;

import com.ricardocreates.translator.util.PropertiesUtil;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserConfig {
    private String defaultApi;
    private String defaultSourceLanguage;
    private String defaultTargetLanguage;
    
    private MicrosoftApiConfig microsoftApiConfig;
    
    public static final String DEFAUL_API_KEY_PROPERTY = "config.defaultApi";
    public static final String DEFAUL_SOURCE_LANGUAGE_PROPERTY = "config.defaultSourceLanguage";
    public static final String DEFAUL_TARGET_LANGUAGE_PROPERTY = "config.defaultTargetLanguage";
    public static final String MICROSOFT_API_SUBCRIPTION_KEY_PROPERTY = "config.api.microsoft.ocpApimSubscriptionKey";
    public static final String MICROSOFT_API_SUBCRIPTION_REGION_PROPERTY = "config.api.microsoft.ocpApimSubscriptionRegion";
    
    public static UserConfig of(File configFile) {
        Map<String, Object> properties = PropertiesUtil.propertiesToMap(configFile);
        
        return UserConfig.builder()
                .defaultApi(properties.getOrDefault(DEFAUL_API_KEY_PROPERTY, TranslatorConfig.DEFAULT_API).toString())
                .defaultSourceLanguage(properties.getOrDefault(DEFAUL_SOURCE_LANGUAGE_PROPERTY, TranslatorConfig.DEFAULT_SOURCE_LANGUAGE_ALFA2).toString())
                .defaultTargetLanguage(properties.getOrDefault(DEFAUL_TARGET_LANGUAGE_PROPERTY, TranslatorConfig.DEFAULT_TARGET_LANGUAGE_ALFA2).toString())
                .microsoftApiConfig(
                        MicrosoftApiConfig.builder()
                        .ocpApimSubscriptionKey(properties.get(MICROSOFT_API_SUBCRIPTION_KEY_PROPERTY).toString())
                        .ocpApimSubscriptionRegion(properties.get(MICROSOFT_API_SUBCRIPTION_REGION_PROPERTY).toString())
                        .build()
                        )
                .build();
    }
}
