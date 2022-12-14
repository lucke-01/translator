package com.ricardocreates.translator.interpreter;

import com.ricardocreates.translator.interpreter.model.Language;

import java.util.List;

/**
 * Interpreter api
 *
 * @author ricardo
 */
public interface InterpreterService {
    /**
     * get a list of available languages
     *
     * @return a list of available languages
     */
    List<Language> getAvailableLanguages();

    /**
     * given a source, target language and a text call return translated text
     *
     * @param sourceLanguage - source language alfa2 format
     * @param destLanguage   - target language alfa2 format
     * @param text           - to be translated
     * @return translation
     */
    String translate(String sourceLanguage, String destLanguage, String text);

    /**
     * represents if this will be translated or if this will display a website with the translation inside
     *
     * @return boolean
     */
    default boolean isTypeBrowser() {
        return false;
    }

    /**
     * represents is this interpreter will allow source and target language or only source with (source and target)
     *
     * @return boolean
     */
    default boolean isTypeOneLanguageSelection() {
        return false;
    }
}
