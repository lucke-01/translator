package com.ricardocreates.translator.interpreter.googletranslator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ricardocreates.translator.interpreter.model.Language;

public interface GoogleInterpreterMapper {
	default Language elementToLanguage(Element element) {
        Language lang = Language.builder().build();

        String locationAlfa2Code = element.attr(GoogleInterpreterService.ATTRIBUTE_DATA_LC);
        
        lang.setAlfa2Code(locationAlfa2Code);
        lang.setDictCode(locationAlfa2Code);
        Element spanChild1 = element.child(0);
        Element spanChild2 = element.child(1);
        
        String firstPartString = spanChild1.text();
        String secondPartString = "";
        
        if (spanChild2.childNodes().size() > 1) {
        	secondPartString = spanChild2.child(0).text() + spanChild2.child(1).text();
        } else {
        	secondPartString = spanChild2.text();
        }
        
        if (StringUtils.isEmpty(firstPartString) && StringUtils.isEmpty(secondPartString)) {
        	if (element.childNodes().size() >= 3) {
        		Element spanChild3 = element.child(2);
        		if (spanChild3.childNodes().size() >= 2) {
        			secondPartString = spanChild3.child(0).text() + " - " + spanChild3.child(1).text();
        		}
        	}
        }
        String finalName;
        if (StringUtils.isEmpty(firstPartString) && StringUtils.isEmpty(secondPartString)) {
        	finalName = "";
        } else if (!StringUtils.isEmpty(firstPartString) && !StringUtils.isEmpty(secondPartString)) {
        	finalName = firstPartString + " - " + secondPartString;
        } else if (!StringUtils.isEmpty(firstPartString)) {
        	finalName = firstPartString;
        } else {
        	finalName = secondPartString;
        }
        lang.setName(finalName);
        return lang;
    }
	default List<Language> elementsToLanguages(Elements elements) {
		List<Language> langs = new ArrayList<>(30);
        for (Element element : elements) {
            Language lang = this.elementToLanguage(element);
            langs.add(lang);
        }
        return langs;
	}
}
