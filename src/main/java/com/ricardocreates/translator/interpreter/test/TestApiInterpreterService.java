package com.ricardocreates.translator.interpreter.test;

import com.ricardocreates.translator.interpreter.InterpreterService;
import com.ricardocreates.translator.interpreter.model.Language;

import java.util.List;

/**
 * dummy implementation only for test app if you do not have access to real api
 */
public class TestApiInterpreterService implements InterpreterService {
    @Override
    public List<Language> getAvailableLanguages() {
        return List.of(
                Language.builder().alfa2Code("es").name("Spanish").nativeName("Spanish").build(),
                Language.builder().alfa2Code("en").name("English").nativeName("English").build()
        );
    }

    @Override
    public String translate(String sourceLanguage, String destLanguage, String text) {
        return "test";
    }
}
