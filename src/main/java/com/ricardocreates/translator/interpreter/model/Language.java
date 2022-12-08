package com.ricardocreates.translator.interpreter.model;

import lombok.Builder;
import lombok.Data;

/**
 * represents a language
 */
@Data
@Builder
public class Language {
    /**
     * lang alf2 code for example: es, en
     */
    private String alfa2Code;
    /**
     * name in english to be shown
     */
    private String name;
    /**
     * native name
     */
    private String nativeName;
    /**
     * used in cambrigde dictionary
     */
    private String dictCode;
}
