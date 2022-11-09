package com.ricardocreates.translator.converter;

import com.ricardocreates.translator.gui.controller.MainAppGuiController;
import com.ricardocreates.translator.gui.converter.StringKeyValuePairConverter;
import com.ricardocreates.translator.interpreter.model.Language;
import com.ricardocreates.translator.model.KeyValuePair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StringKeyValuePairConverterTest {
    private final static String ES_ALFA2 = "es";
    private final static String ES_VALUE = "esp";
    private final static String EN_ALFA2 = "en";
    private final static String EN_VALUE = "eng";
    @Mock
    private MainAppGuiController mainAppGuiController;
    @InjectMocks
    private StringKeyValuePairConverter stringKeyValuePairConverter;

    @Test
    void should_get_fromString() {
        //given
        given(mainAppGuiController.getLanguages()).willReturn(mockLanguages());
        //when
        KeyValuePair<String, String> lang = stringKeyValuePairConverter.fromString(EN_VALUE);
        //then
        assertThat(lang.getKey(), is(equalTo(EN_ALFA2)));
    }

    @Test
    void should_not_get_fromString_notFound() {
        //given
        String notFoundLang = "notFound";
        given(mainAppGuiController.getLanguages()).willReturn(mockLanguages());
        //when
        KeyValuePair<String, String> lang = stringKeyValuePairConverter.fromString(notFoundLang);
        //then
        assertThat(lang, is(nullValue()));
    }

    @Test
    void should_not_get_fromString_languagesNull() {
        //given
        given(mainAppGuiController.getLanguages()).willReturn(null);
        //when
        KeyValuePair<String, String> lang = stringKeyValuePairConverter.fromString(EN_VALUE);
        //then
        assertThat(lang, is(nullValue()));
    }

    @Test
    void should_not_get_fromString_langName() {
        //given
        String noLang = null;
        given(mainAppGuiController.getLanguages()).willReturn(null);
        //when
        KeyValuePair<String, String> lang = stringKeyValuePairConverter.fromString(noLang);
        //then
        assertThat(lang, is(nullValue()));
    }

    private List<Language> mockLanguages() {
        return List.of(
                Language.builder()
                        .alfa2Code(ES_ALFA2)
                        .name(ES_VALUE)
                        .build(),
                Language.builder()
                        .alfa2Code(EN_ALFA2)
                        .name(EN_VALUE)
                        .build()
        );
    }
}
