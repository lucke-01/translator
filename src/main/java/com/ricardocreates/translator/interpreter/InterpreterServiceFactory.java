package com.ricardocreates.translator.interpreter;

import com.ricardocreates.translator.config.TranslatorConfig;
import com.ricardocreates.translator.interpreter.microsoft.MicrosfotApiInterpreterService;

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
            case TranslatorConfig.MICROSOFT_API_KEY: {
                return new MicrosfotApiInterpreterService();
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + interpreterApiKey);
        }
    }
}
