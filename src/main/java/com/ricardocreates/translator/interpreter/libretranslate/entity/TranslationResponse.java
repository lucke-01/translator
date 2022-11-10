package com.ricardocreates.translator.interpreter.libretranslate.entity;

import lombok.Data;

@Data
public class TranslationResponse {
    private String translatedText;

    @Override
    public String toString() {
        return this.translatedText;
    }
}
