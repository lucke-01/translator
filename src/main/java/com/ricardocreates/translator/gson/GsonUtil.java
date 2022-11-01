package com.ricardocreates.translator.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Use to operate with Gson
 */
public class GsonUtil {
    /**
     * singleton gson
     */
    public static Gson gson;

    private GsonUtil() {
    }

    /**
     * creates or returns gson object
     *
     * @return a gson object
     */
    public static Gson getGson() {
        if (gson == null) {
            gson = createGson();
        }
        return gson;
    }

    /**
     * create and configure gson object
     *
     * @return a gson object
     */
    private static Gson createGson() {
        GsonBuilder builder = new GsonBuilder();

        return builder.create();
    }
}