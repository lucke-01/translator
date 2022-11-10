package com.ricardocreates.translator.interpreter.libretranslate;

import com.ricardocreates.translator.interpreter.libretranslate.entity.LanguageResponse;
import com.ricardocreates.translator.interpreter.model.Language;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface LibreTranslateMapper {

    List<Language> toLanguageList(List<LanguageResponse> languages);

    @Mapping(target = "alfa2Code", source = "code")
    @Mapping(target = "name", source = "name")
    Language toLanguage(LanguageResponse langResponse);
}
