package com.ricardocreates.translator.interpreter;

import java.util.List;

import com.ricardocreates.translator.interpreter.model.Language;

/**
 * Interpreter api
 * @author ricardo
 *
 */
public interface InterpreterService {
    /**
     * get a list of available languages
     * @return a list of available languages
     */
    public List<Language> getAvailableLanguages();
    /**
     * 
     * given a source, target language and a text call return translated text
     * 
     * @param sourceLanguage - alfa2 format
     * @param destLanguage - alfa2 format
     * @param text
     * @return
     */
    public String translate(String sourceLanguage, String destLanguage, String text);
}
