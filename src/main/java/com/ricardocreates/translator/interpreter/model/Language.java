package com.ricardocreates.translator.interpreter.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Language {
    private String alfa2Code;
    private String name;
    private String nativeName;
}
