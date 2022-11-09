package com.ricardocreates.translator.config;

import lombok.Builder;
import lombok.Data;

/**
 * Model of configuration file
 */
@Data
@Builder
public class MicrosoftApiConfig {
    /**
     * microsoft api key
     */
    private String ocpApimSubscriptionKey;
    /**
     * desired region
     */
    private String ocpApimSubscriptionRegion;
}
