package com.ricardocreates.translator.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PropertiesUtilTest {
    @Test
    @DisplayName("should map property file to Map")
    void should_map_file_toHashMap() {
        //given
        File fileProperties = FileUtil.getFileFromResourcePath("configExample.properties");
        //when
        Map<String, Object> propertiesMap = PropertiesUtil.propertiesToMap(fileProperties);
        //then
        assertThat(propertiesMap, is(notNullValue()));
        assertThat(propertiesMap.get("config.defaultApi"), is(notNullValue()));
        assertThat(propertiesMap.get("config.defaultApi"), is(equalTo("microsoft")));
    }
    @Test
    @DisplayName("should no map property file to Map file not found")
    void should_no_map_file_toHashMap_fileNotFound() {
        //given
        File fileProperties = FileUtil.getFileFromResourcePath("notFound.properties");
        //when and then
        assertThrows(IllegalArgumentException.class, () -> PropertiesUtil.propertiesToMap(fileProperties));
    }
}
