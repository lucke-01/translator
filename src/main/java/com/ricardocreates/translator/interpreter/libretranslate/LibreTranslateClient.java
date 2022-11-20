package com.ricardocreates.translator.interpreter.libretranslate;

import java.util.List;

import com.ricardocreates.translator.interpreter.libretranslate.entity.LanguageResponse;
import com.ricardocreates.translator.interpreter.libretranslate.entity.TranslationRequest;
import com.ricardocreates.translator.interpreter.libretranslate.entity.TranslationResponse;

import feign.Headers;
import feign.RequestLine;

public interface LibreTranslateClient {
    @RequestLine("POST /translate")
    @Headers("Content-Type: application/json")
    TranslationResponse translate(TranslationRequest translationRequest);

    @RequestLine("GET /languages")
    @Headers("Content-Type: application/json")
    List<LanguageResponse> getLanguages();
}
