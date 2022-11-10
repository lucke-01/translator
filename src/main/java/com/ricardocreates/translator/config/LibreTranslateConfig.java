package com.ricardocreates.translator.config;

import lombok.Builder;
import lombok.Data;

/**
 * Model of configuration file
 */
@Data
@Builder
public class LibreTranslateConfig {
    private String host;
    private String apiKey;
}
