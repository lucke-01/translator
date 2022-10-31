package com.ricardocreates.translator.interpreter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ricardocreates.translator.interpreter.microsoft.MicrosfotApiInterpreterService;
import com.ricardocreates.translator.interpreter.model.Language;

class MicrosoftApiInterpreterIT {
    private MicrosfotApiInterpreterService microsoftInterpreter = new MicrosfotApiInterpreterService("http://localhost:8000/");
    static WireMockServer wireMockServer;
    @BeforeAll
    static void setUp() {
        //will look for src/test/resources/mappings/*.json stub files
        wireMockServer = new WireMockServer( 8000);
        wireMockServer.start();
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
