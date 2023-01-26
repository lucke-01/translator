package com.ricardocreates.translator.interpreter;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ricardocreates.translator.interpreter.cambridge.CambridgeInterpreterService;
import com.ricardocreates.translator.interpreter.googletranslator.GoogleInterpreterService;
import com.ricardocreates.translator.interpreter.model.Language;

public class GoogleInterpreterServiceTest {
	private GoogleInterpreterService cambridgeInterpreter = new GoogleInterpreterService();
	@Test
    void should_get_availableLanguage() {
        //GIVEN
        //wiremock in src/test/resources/mappings/cambridge-dictionary-page.json
        //WHEN
        List<Language> languages = cambridgeInterpreter.getAvailableLanguages();
        System.out.println(languages);
        //THEN
    }
}
