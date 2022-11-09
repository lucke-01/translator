package com.ricardocreates.translator.gui.converter;

import com.ricardocreates.translator.gui.controller.MainAppGuiController;
import com.ricardocreates.translator.interpreter.model.Language;
import com.ricardocreates.translator.model.KeyValuePair;
import javafx.util.StringConverter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * converter from language string to language and description and vice-versa
 */
@NoArgsConstructor
public class StringKeyValuePairConverter extends StringConverter<KeyValuePair<String, String>> {
    /**
     * controller to communicate
     */
    private MainAppGuiController mainAppGuiController;

    /**
     * creation given a controller
     *
     * @param mainAppGuiController - controller
     */
    public StringKeyValuePairConverter(MainAppGuiController mainAppGuiController) {
        super();
        this.mainAppGuiController = mainAppGuiController;
    }

    // Method to convert a Customer-Object to a String
    @Override
    public String toString(KeyValuePair<String, String> value) {
        return value.getValue();
    }

    // Method to convert a String to a Customer-Object
    @Override
    public KeyValuePair<String, String> fromString(String langName) {
        KeyValuePair<String, String> valuePair = null;
        List<Language> languages = mainAppGuiController.getLanguages();

        if (langName != null && languages != null) {
            Optional<Language> foundLanguage = languages.stream().filter(lang -> lang.getName().equals(langName)).findFirst();
            if (foundLanguage.isPresent()) {
                valuePair = new KeyValuePair<String, String>(foundLanguage.get().getAlfa2Code(), foundLanguage.get().getName());
            }
        }
        return valuePair;
    }

}
