package com.ricardocreates.translator.interpreter;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.ricardocreates.translator.interpreter.cambridge.CambridgeInterpreterService;
import com.ricardocreates.translator.interpreter.model.Language;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class CambridgeInterpreterServiceTest {
    //new CambridgeInterpreterService("http://localhost:8000");
    static WireMockServer wireMockServer;
    private CambridgeInterpreterService cambridgeInterpreter = new CambridgeInterpreterService();

    @BeforeAll
    static void setUp() {
        //will look for src/test/resources/mappings/*.json stub files
        //wireMockServer = new WireMockServer(8000);
        //wireMockServer.start();
    }

    @Test
    void should_get_availableLanguage() {
        //GIVEN
        //wiremock in src/test/resources/mappings/cambridge-dictionary-page.json
        //WHEN
        List<Language> languages = cambridgeInterpreter.getAvailableLanguages();
        System.out.println(languages);
        //THEN
    }

    @Test
    void should_translate() {
        //GIVEN
        //wiremock in src/test/resources/mappings/cambridge-dictionary-page.json
        //WHEN
        String textTranslated = cambridgeInterpreter.translate("english-spanish", null, "hello");
        System.out.println(textTranslated);
        //THEN
    }
}
