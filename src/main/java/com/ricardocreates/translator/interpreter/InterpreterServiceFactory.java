package com.ricardocreates.translator.interpreter;

import com.ricardocreates.translator.config.TranslatorConfig;
import com.ricardocreates.translator.interpreter.microsoft.MicrosfotApiInterpreterService;
import com.ricardocreates.translator.interpreter.test.TestApiInterpreterService;

/**
 * Factory to get an interpreter service
 */
public class InterpreterServiceFactory {
    private InterpreterServiceFactory() {
    }

    /**
     * given a desired interpreter returns it.
     *
     * @param interpreterApiKey - interpreter identifier
     * @return a Interpreter service
     */
    public static InterpreterService getInterpreterService(String interpreterApiKey) {
        switch (interpreterApiKey) {
            case TranslatorConfig.MICROSOFT_API_KEY:
                return new MicrosfotApiInterpreterService();
            case TranslatorConfig.TEST_API_KEY:
                return new TestApiInterpreterService();
            default:
                throw new IllegalArgumentException("Unexpected value: " + interpreterApiKey);
        }
    }
}
