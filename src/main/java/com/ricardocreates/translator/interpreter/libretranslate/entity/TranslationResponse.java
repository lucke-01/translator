package com.ricardocreates.translator.interpreter.libretranslate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslationResponse {
    private String translatedText;


    @Override
    public String toString() {
        return this.translatedText;
    }
}
