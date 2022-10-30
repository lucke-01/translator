package com.ricardocreates.translator.util;

/**
 * String util class
 * @author ricardo
 *
 */
public class StringsCustomUtil {
    private StringsCustomUtil() {}
    /**
     * given a json string which begins with array like: [{...}] 
     * remove the first and the last line to get only an object
     * 
     * this is useful in special kind of apis which returns odd json format responses
     * @param jsonArrayString
     * @return
     */
    public static String jsonArrayStringToJsonObjectString(String jsonArrayString) {
        return jsonArrayString.substring(1, jsonArrayString.length()-1);
    }
}
