package com.ricardocreates.translator.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SystemInfoTest {
    @Test
    @DisplayName("should_return java version")
    void should_return_javaVersion() {
        //given
        //when
        String javaVersion = SystemInfo.javaVersion();
        //then
        assertThat(javaVersion, is(not(emptyOrNullString())));
    }
    @Test
    @DisplayName("should_return java fx version")
    void should_return_javafxVersion() {
        //given
        //when and then
        assertDoesNotThrow(() -> SystemInfo.javafxVersion());
    }
}
