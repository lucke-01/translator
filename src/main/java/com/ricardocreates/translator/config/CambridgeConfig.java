package com.ricardocreates.translator.config;

import lombok.Builder;
import lombok.Data;

/**
 * Model of configuration file
 */
@Data
@Builder
public class CambridgeConfig {
    private String defaultLanguage;
}
