package com.ricardocreates.translator.interpreter.libretranslate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageResponse {
    private String code;
    private String name;
}
