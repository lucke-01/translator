package com.ricardocreates.translator.interpreter.libretranslate.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslationRequest {
    /**
     * text to be translate
     */
    private String q;
    private String source = "en";
    private String target = "es";
    private String format = "text";
    private String api_key = "";
}
