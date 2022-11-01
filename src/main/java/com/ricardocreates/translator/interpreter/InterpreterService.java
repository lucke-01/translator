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
    public List<Language> getAvailableLanguages();

    /**
     * given a source, target language and a text call return translated text
     *
     * @param sourceLanguage - source language alfa2 format
     * @param destLanguage   - target language alfa2 format
     * @param text           - to be translated
     * @return translation
     */
    public String translate(String sourceLanguage, String destLanguage, String text);
}
