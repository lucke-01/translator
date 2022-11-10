package com.ricardocreates.translator.interpreter;

import com.ricardocreates.translator.config.TranslatorConfig;
import com.ricardocreates.translator.interpreter.libretranslate.LibreTranslateService;
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
        return switch (interpreterApiKey) {
            case TranslatorConfig.MICROSOFT_API_KEY -> new MicrosfotApiInterpreterService();
            case TranslatorConfig.TEST_API_KEY -> new TestApiInterpreterService();
            case TranslatorConfig.LIBRE_TRANSLATE_API_KEY ->
                    new LibreTranslateService(TranslatorConfig.getUserConfig().getLibreTranslateConfig().getHost());
            default -> throw new IllegalArgumentException("Unexpected value: " + interpreterApiKey);
        };
    }
}
