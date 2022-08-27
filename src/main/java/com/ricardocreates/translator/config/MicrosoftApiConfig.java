package com.ricardocreates.translator.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MicrosoftApiConfig {
    private String ocpApimSubscriptionKey;
    private String ocpApimSubscriptionRegion;
}
