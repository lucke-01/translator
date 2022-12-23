package com.ricardocreates.translator.interpreter.cambridge;

import com.ricardocreates.translator.interpreter.InterpreterService;
import com.ricardocreates.translator.interpreter.model.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * dummy implementation only for test app if you do not have access to real api
 */
@NoArgsConstructor
@AllArgsConstructor
public class CambridgeInterpreterService implements InterpreterService {

    public static final String ATTRIBUTE_DICT_CODE = "data-dictcode";
    private static final String DICTIONARY_PATH = "dictionary";
    private String mainUrl = "https://dictionary.cambridge.org";

    private CambridgeInterpreterMapper cambridgeInterpreterMapper = Mappers.getMapper(CambridgeInterpreterMapper.class);

    @Override
    public List<Language> getAvailableLanguages() {
        List<Language> languages = new ArrayList<>(30);
        String dictionaryPath = String.format("%s", mainUrl);
        Document doc;
        try {
            doc = Jsoup.connect(dictionaryPath).get();
            Elements dictionaryLinks = doc.select("ul a[data-dictCode]");
            //dictionaryLinks.forEach(System.out::println);
            return cambridgeInterpreterMapper.elementsToLanguages(dictionaryLinks);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String translate(String sourceLanguage, String destLanguage, String text) {
        String translationPath = String.format("%s/%s/%s/%s", mainUrl, DICTIONARY_PATH, sourceLanguage, text);
        return translationPath;
        /*Document doc;
        try {
            doc = Jsoup.connect(translationPath).get();
            Elements dictionaryLinks = doc.select("*");
            dictionaryLinks.forEach(System.out::println);
            return translation;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }*/
    }

    @Override
    public boolean isTypeBrowser() {
        return true;
    }
}
