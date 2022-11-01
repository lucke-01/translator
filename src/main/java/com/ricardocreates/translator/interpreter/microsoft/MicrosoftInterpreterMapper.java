package com.ricardocreates.translator.interpreter.microsoft;

import com.ricardocreates.translator.interpreter.microsoft.entity.LanguageEntity;
import com.ricardocreates.translator.interpreter.microsoft.entity.LanguageResponseEntity;
import com.ricardocreates.translator.interpreter.model.Language;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * microsoft interpreter mapper
 */
@Mapper
public interface MicrosoftInterpreterMapper {

    default List<Language> languageResponseToListLanguage(LanguageResponseEntity languageResponseEntity) {
        List<Language> languages = new ArrayList<>();

        if (languageResponseEntity != null) {
            Map<String, LanguageEntity> translation = languageResponseEntity.getTranslation();
            if (translation != null) {
                for (var languageTranslationEntry : translation.entrySet()) {
                    Language language = translationEntrySetToLanguage(languageTranslationEntry.getKey(), languageTranslationEntry.getValue());
                    languages.add(language);
                }

            }
        }
        return languages;
    }

    default Language translationEntrySetToLanguage(String key, LanguageEntity languageValue) {
        return Language.builder()
                .alfa2Code(key)
                .name(languageValue.getName())
                .nativeName(languageValue.getNativeName())
                .build();
    }
}
