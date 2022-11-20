package com.ricardocreates.translator.interpreter.libretranslate;

import java.util.List;

import org.mapstruct.factory.Mappers;

import com.ricardocreates.translator.interpreter.InterpreterService;
import com.ricardocreates.translator.interpreter.libretranslate.entity.LanguageResponse;
import com.ricardocreates.translator.interpreter.libretranslate.entity.TranslationRequest;
import com.ricardocreates.translator.interpreter.libretranslate.entity.TranslationResponse;
import com.ricardocreates.translator.interpreter.model.Language;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

/**
 * dummy implementation only for test app if you do not have access to real api
 */
public class LibreTranslateService implements InterpreterService {
    private final LibreTranslateMapper libreTranslateMapper = Mappers.getMapper(LibreTranslateMapper.class);
    private String apiUrl;
    private LibreTranslateClient libreTranslateClient;

    public LibreTranslateService() {
        this.init();
    }

    public LibreTranslateService(String apiUrl) {
        this.apiUrl = apiUrl;
        this.init();
    }

    public LibreTranslateService(LibreTranslateClient libreTranslateClient) {
        this.libreTranslateClient = libreTranslateClient;
    }

    public void init() {
        OkHttpClient okHttpClient = new OkHttpClient();
        
        this.libreTranslateClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                //.logger(new Slf4jLogger(LibreTranslateClient.class))
                .logLevel(Logger.Level.FULL)
                .target(LibreTranslateClient.class, apiUrl);
    }

    @Override
    public List<Language> getAvailableLanguages() {
        List<LanguageResponse> languagesResponse = this.libreTranslateClient.getLanguages();
        try {
            Thread.sleep(1* 5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return this.libreTranslateMapper.toLanguageList(languagesResponse);
    }

    @Override
    public String translate(String sourceLanguage, String destLanguage, String text) {
        TranslationRequest translationRequest = TranslationRequest.builder().source(sourceLanguage).target(destLanguage).q(text).build();
        TranslationResponse translationResponse = this.libreTranslateClient.translate(translationRequest);
        return translationResponse.getTranslatedText();
    }
}
