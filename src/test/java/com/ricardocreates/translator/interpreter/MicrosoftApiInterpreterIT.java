package com.ricardocreates.translator.interpreter;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.ricardocreates.translator.interpreter.microsoft.MicrosfotApiInterpreterService;
import com.ricardocreates.translator.interpreter.model.Language;
import com.ricardocreates.translator.util.FileUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MicrosoftApiInterpreterIT {
    static WireMockServer wireMockServer;
    private MicrosfotApiInterpreterService microsoftInterpreter = new MicrosfotApiInterpreterService("http://localhost:8000/");

    @BeforeAll
    static void setUp() {
        //will look for src/test/resources/mappings/*.json stub files
        wireMockServer = new WireMockServer(8000);
        wireMockServer.start();
        System.setProperty("configFile", FileUtil.getFileFromResourcePath("configExample.properties").toString());
    }

    @Test
    void should_get_availableLanguage() {
        //GIVEN
        //wiremock in src/test/resources/mappings/microsoft-languageapi.json
        //WHEN
        List<Language> languages = microsoftInterpreter.getAvailableLanguages();
        //THEN
        assertThat(languages, notNullValue());
        assertThat(languages.get(0), notNullValue());
        assertThat(languages.get(0).getAlfa2Code(), not(emptyOrNullString()));
        assertThat(languages.get(0).getName(), not(emptyOrNullString()));
        assertThat(languages.get(0).getNativeName(), not(emptyOrNullString()));
    }

    @Test
    void should_translate_text() {
        //GIVEN
        //wiremock in src/test/resources/mappings/microsoft-languageapi.json
        String sourceLanguage = "es";
        String destLanguage = "en";
        String text = "hola";
        //WHEN
        String translatedText = microsoftInterpreter.translate(sourceLanguage, destLanguage, text);
        //THEN
        assertThat(translatedText, notNullValue());
        assertThat(translatedText, is(equalTo("hello")));
    }
}
