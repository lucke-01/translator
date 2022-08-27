package com.ricardocreates.translator.util;

public class StringsCustomUtil {
    public static String jsonArrayStringToJsonObjectString(String jsonArrayString) {
        return jsonArrayString.substring(1, jsonArrayString.length()-1);
    }
    public static void main (String[] ar) {
        System.out.println(jsonArrayStringToJsonObjectString("hola"));
    }
}
