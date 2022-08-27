package com.ricardocreates.translator.interpreter;

import com.ricardocreates.translator.config.TranslatorConfig;
import com.ricardocreates.translator.interpreter.microsoft.MicrosfotApiInterpreterService;

public class InterpreterServiceFactory {
    private InterpreterServiceFactory() {}
    
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
