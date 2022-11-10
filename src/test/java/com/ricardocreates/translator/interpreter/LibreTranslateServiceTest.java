package com.ricardocreates.translator.interpreter;

import com.ricardocreates.translator.interpreter.libretranslate.LibreTranslateClient;
import com.ricardocreates.translator.interpreter.libretranslate.LibreTranslateService;
import com.ricardocreates.translator.interpreter.libretranslate.entity.LanguageResponse;
import com.ricardocreates.translator.interpreter.libretranslate.entity.TranslationResponse;
import com.ricardocreates.translator.interpreter.model.Language;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LibreTranslateServiceTest {
    private final static String ES_ALFA2 = "es";
    private final static String ES_VALUE = "esp";
    private final static String EN_ALFA2 = "en";
    private final static String EN_VALUE = "eng";

    @Mock
    private LibreTranslateClient libreTranslateClient;
    @InjectMocks
    private LibreTranslateService libreTranslateService;

    @Test
    void should_translate_text() {
        //given
        String translationResponse = "hello";
        given(libreTranslateClient.translate(any())).willReturn(new TranslationResponse(translationResponse));
        //when
        String translation = this.libreTranslateService.translate(EN_ALFA2, ES_ALFA2, translationResponse);
        //then
        assertThat(translation, is(notNullValue()));
        assertThat(translation, is(equalTo(translationResponse)));
        verify(libreTranslateClient, times(1)).translate(any());
    }

    @Test
    void should_return_languages() {
        //given
        given(libreTranslateClient.getLanguages()).willReturn(mockLanguages());
        //when
        List<Language> languages = this.libreTranslateService.getAvailableLanguages();

        //then
        assertThat(languages, is(notNullValue()));
        assertThat(languages, hasSize(2));
        assertThat(languages.get(0).getAlfa2Code(), is(equalTo(ES_ALFA2)));
        assertThat(languages.get(0).getName(), is(equalTo(ES_VALUE)));
        verify(libreTranslateClient, times(1)).getLanguages();
    }

    private List<LanguageResponse> mockLanguages() {
        return List.of(
                new LanguageResponse(ES_ALFA2, ES_VALUE),
                new LanguageResponse(EN_ALFA2, EN_VALUE)
        );
    }
}
