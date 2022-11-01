package com.ricardocreates.translator.interpreter;

import com.ricardocreates.translator.util.StringsCustomUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InterpreterServiceFactoryTest {

    @DisplayName("should return a interpreter given a desired interpreter")
    @ParameterizedTest
    //we can add here new interpreters
    @CsvSource(value = {"microsoft:MicrosfotApiInterpreterService"}, delimiter = ':')
    void should_return_interpreter_given_interpreter(String interpreterKey, String expectedInterpreter) {
        //given: method parameters
        //when
        InterpreterService interpreter = InterpreterServiceFactory.getInterpreterService(interpreterKey);
        //then
        assertThat(interpreter, is(notNullValue()));
        assertThat(interpreter.getClass().getSimpleName(), is(equalTo(expectedInterpreter)));
    }
    @Test
    @DisplayName("should not found interpreter and throws an exception")
    void should_noFound_interpreter() {
        //given
        String noInterpreter = "notFoundInterpreter";
        //when and then
        assertThrows(IllegalArgumentException.class, () -> InterpreterServiceFactory.getInterpreterService(noInterpreter));
    }
}
