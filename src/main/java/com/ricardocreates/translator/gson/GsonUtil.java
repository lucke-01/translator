package com.ricardocreates.translator.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    
    public static Gson GSON;

    public static Gson getGson() {
        if (GSON == null) {
            GSON = createGson();
        }
        return GSON;
    }
    private static Gson createGson() {
        GsonBuilder builder = new GsonBuilder();
        //builder.registerTypeAdapter(new TypeToken<ArrayList<Language>>() {}.getType(), new TranslationMapDeserializer());
        
        return builder.create();
    }
}