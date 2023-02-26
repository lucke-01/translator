package com.ricardocreates.translator.interpreter.microsoft.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
//TODO: delete all this class
public class TranslationMapDeserializer implements JsonDeserializer<List<LanguageEntity>> {
    @Override
    public List<LanguageEntity> deserialize(JsonElement element, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<LanguageEntity> languageList = new ArrayList<>();

        JsonObject parentJsonObject = element.getAsJsonObject();
        Map.Entry<String, JsonElement> entry = parentJsonObject.entrySet().iterator().next();

        Iterator<JsonElement> iterator = entry.getValue().getAsJsonArray().iterator();
        LanguageEntity language;
        while (iterator.hasNext()){
            JsonObject cityJsonObject = iterator.next().getAsJsonObject();
            for(Map.Entry<String, JsonElement> entry1 : cityJsonObject.entrySet()){
                language = new LanguageEntity();
                Object value = entry1.getValue();
            }
        }
        return languageList;
    }
}