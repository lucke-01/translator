package com.ricardocreates.translator.interpreter.cambridge;

import com.ricardocreates.translator.interpreter.model.Language;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CambridgeInterpreterMapper {

    default Language elementToLanguage(Element element) {
        Language lang = Language.builder().build();

        lang.setName(element.html());
        lang.setAlfa2Code(element.attr(CambridgeInterpreterService.ATTRIBUTE_DICT_CODE));
        lang.setDictCode(element.attr(CambridgeInterpreterService.ATTRIBUTE_DICT_CODE));

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
