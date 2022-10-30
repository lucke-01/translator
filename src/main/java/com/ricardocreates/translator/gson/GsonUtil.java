package com.ricardocreates.translator.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    
    private GsonUtil() {}
    
    public static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = createGson();
        }
        return gson;
    }
    private static Gson createGson() {
        GsonBuilder builder = new GsonBuilder();
        
        return builder.create();
    }
}