package com.ricardocreates.translator.interpreter;

import com.ricardocreates.translator.interpreter.libretranslate.LibreTranslateService;
import com.ricardocreates.translator.interpreter.model.Language;
import org.junit.jupiter.api.Test;

import java.util.List;

class LibreTranslateServiceTest {
    private LibreTranslateService libreTranslateService = new LibreTranslateService();

    @Test
    void should_translate_text() {
        String translation = this.libreTranslateService.translate("en", "es", "hello");
        System.out.println(translation);
    }

    @Test
    void should_return_languages() {
        List<Language> languages = this.libreTranslateService.getAvailableLanguages();
        System.out.println(languages);
    }
}
