package com.ricardocreates.translator.interpreter.googletranslator;

import com.ricardocreates.translator.interpreter.InterpreterService;
import com.ricardocreates.translator.interpreter.InterpreterWebView;
import com.ricardocreates.translator.interpreter.model.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;

/**
 * dummy implementation only for test app if you do not have access to real api
 */
@NoArgsConstructor
@AllArgsConstructor
public class GoogleInterpreterService implements InterpreterService, InterpreterWebView {

    private String mainUrl = "https://translate.google.com";

    private GoogleInterpreterMapper cambridgeInterpreterMapper = new GoogleInterpreterMapper() {};

    @Override
    public List<Language> getAvailableLanguages() {
        String dictionaryPath = String.format("%s", mainUrl);
        Document doc;
        try {
            doc = Jsoup.connect(dictionaryPath).get();
            Elements dictionaryLinks = doc.select("ul a[data-dictCode]");
            //dictionaryLinks.forEach(System.out::println);
            //return cambridgeInterpreterMapper.elementsToLanguages(dictionaryLinks);
            return null;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String translate(String sourceLanguage, String destLanguage, String text) {
        //String translationPath = String.format("%s/%s/%s/%s", mainUrl, DICTIONARY_PATH, sourceLanguage, text);
        //return translationPath;
    	return "";
    }

    @Override
    public boolean isTypeBrowser() {
        return true;
    }

    @Override
    public boolean isTypeOneLanguageSelection() {
        return true;
    }
}
