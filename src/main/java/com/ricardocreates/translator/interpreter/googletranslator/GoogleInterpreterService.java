package com.ricardocreates.translator.interpreter.googletranslator;

import com.ricardocreates.translator.interpreter.InterpreterService;
import com.ricardocreates.translator.interpreter.InterpreterWebView;
import com.ricardocreates.translator.interpreter.model.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * dummy implementation only for test app if you do not have access to real api
 */
@NoArgsConstructor
@AllArgsConstructor
public class GoogleInterpreterService implements InterpreterService, InterpreterWebView {
	
	public static final String ATTRIBUTE_DATA_LC = "data-lc";
	public static final String TRANSLATE_OPERATION = "translate";

    private String mainUrl = "https://translate.google.com";
    
    private String defaultHl = "es";

    private GoogleInterpreterMapper googleInterpreterMapper = new GoogleInterpreterMapper() {};

    @Override
    public List<Language> getAvailableLanguages() {
        String dictionaryPath = String.format("%s", mainUrl);
        Document doc;
        try {
            doc = Jsoup.connect(dictionaryPath).get();
            Elements languageLi = doc.select(String.format("li[%s]", ATTRIBUTE_DATA_LC));
            return googleInterpreterMapper.elementsToLanguages(languageLi);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String translate(String sourceLanguage, String destLanguage, String text) {
    	//example https://translate.google.com/?hl=es&sl=es&tl=en&text=hola&op=translate
    	String translationPath = String.format("%s/?hl=%s&sl=%s&tl=%s&text=%s&op=%s", mainUrl, defaultHl, sourceLanguage, destLanguage, text, TRANSLATE_OPERATION);
        return translationPath;
    }
}
