package com.ricardocreates.translator.interpreter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ricardocreates.translator.interpreter.microsoft.MicrosfotApiInterpreterService;
import com.ricardocreates.translator.interpreter.model.Language;
public class MicrosoftApiInterpreterIT {
    
    private InterpreterService microsoftInterpreter = new MicrosfotApiInterpreterService();
    
    @Test
    public void should_get_availableLanguage() {
        //GIVEN
        // microsoft api rest working
        //WHEN
        List<Language> languages = microsoftInterpreter.getAvailableLanguages();
        //THEN
        assertThat(languages, notNullValue());
        assertThat(languages.get(0), notNullValue());
        assertThat(languages.get(0).getAlfa2Code(), not(isEmptyOrNullString()));
        assertThat(languages.get(0).getName(), not(isEmptyOrNullString()));
        assertThat(languages.get(0).getNativeName(), not(isEmptyOrNullString()));
    }
}
