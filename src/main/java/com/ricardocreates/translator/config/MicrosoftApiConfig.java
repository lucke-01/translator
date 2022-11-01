package com.ricardocreates.translator.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * Model of configuration file
 */
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
