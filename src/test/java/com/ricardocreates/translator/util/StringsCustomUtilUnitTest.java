package com.ricardocreates.translator.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringsCustomUtilUnitTest {
    @Test
    @DisplayName("should_remove array from json text")
    void should_remove_array_from_json_text() {
        //given
        final String arrayJson = "[{\"key\": \"value\"}]";
        //when
        String finalJsonObj = StringsCustomUtil.jsonArrayStringToJsonObjectString(arrayJson);
        //then
        assertThat(finalJsonObj, is(notNullValue()));
        assertThat(finalJsonObj, is(equalTo("{\"key\": \"value\"}")));
    }
    @Test
    @DisplayName("should_no remove array from json text null value")
    void should_no_remove_array_from_json_text_nullValue() {
        //given
        final String arrayJson = null;
        //when and then
        assertThrows(NullPointerException.class, () -> StringsCustomUtil.jsonArrayStringToJsonObjectString(arrayJson));
    }
    @Test
    @DisplayName("should_no remove array from json text too short value")
    void should_no_remove_array_from_json_text_tooShortValue() {
        //given
        final String arrayJson = "";
        //when and then
        assertThrows(StringIndexOutOfBoundsException.class, () -> StringsCustomUtil.jsonArrayStringToJsonObjectString(arrayJson));
    }
}
