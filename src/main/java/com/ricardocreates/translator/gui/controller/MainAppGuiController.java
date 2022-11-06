package com.ricardocreates.translator.gui.controller;

import com.ricardocreates.translator.config.TranslatorConfig;
import com.ricardocreates.translator.config.UserConfig;
import com.ricardocreates.translator.gui.component.AutoCompleteComboBoxListener;
import com.ricardocreates.translator.gui.converter.StringKeyValuePairConverter;
import com.ricardocreates.translator.interpreter.InterpreterService;
import com.ricardocreates.translator.interpreter.InterpreterServiceFactory;
import com.ricardocreates.translator.interpreter.model.Language;
import com.ricardocreates.translator.model.KeyValuePair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main App controller
 */
public class MainAppGuiController implements Initializable {
    private final UserConfig userConfig = TranslatorConfig.getUserConfig();
    @FXML
    private MenuItem menuHelpAbout;
    @FXML
    private ComboBox<KeyValuePair<String, String>> comboLanguage1;
    @FXML
    private ComboBox<KeyValuePair<String, String>> comboLanguage2;
    @FXML
    private ComboBox<KeyValuePair<String, String>> comboTranslatorApi;
    @FXML
    @Getter
    private TextArea textAreaLanguage1;
    @FXML
    private TextArea textAreaLanguage2;
    private AutoCompleteComboBoxListener<KeyValuePair<String, String>> autoComboLanguage1;
    private AutoCompleteComboBoxListener<KeyValuePair<String, String>> autoComboLanguage2;
    @Getter
    private List<Language> languages = new ArrayList<>();
    @Getter
    private LocalDateTime lastKeyPressedTime;
    private InterpreterService interpreterService;
    private DelayTextRunnable delayTextThread;

    @FXML
    void menuShowAboutAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/mainApp/aboutGui.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(root, 630, 400);
            Stage stage = new Stage();
            stage.setTitle("About");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void comboTranslatorApiAction(ActionEvent event) {
        interpreterService = InterpreterServiceFactory.getInterpreterService(comboTranslatorApi.getValue().getKey());
        fillLanguages();
    }

    @FXML
    void textAreaLanguage1OnKeyPressed(KeyEvent event) {
        this.setLastKeyPressedTime(LocalDateTime.now());
    }

    @FXML
    void revertButtonOnAction(ActionEvent event) {
        final KeyValuePair comboLanguage1Value = this.comboLanguage1.getValue();

        comboLanguage1.setValue(comboLanguage2.getValue());
        comboLanguage2.setValue(comboLanguage1Value);
    }

    @FXML
    void editMenuPaste(ActionEvent event) {
        textAreaLanguage1.paste();
    }

    @FXML
    void editMenuCut(ActionEvent event) {
        textAreaLanguage1.cut();
    }

    @FXML
    void editMenuCopy(ActionEvent event) {
        textAreaLanguage1.copy();
    }

    @FXML
    void editMenuUndo() {
        textAreaLanguage1.undo();
    }

    @FXML
    void editMenuRedo() {
        textAreaLanguage1.redo();
    }

    @FXML
    void editMenuUnselectAll() {
        textAreaLanguage1.deselect();
    }

    @FXML
    void editMenuSelectAll() {
        textAreaLanguage1.selectAll();
    }

    @FXML
    void menuItemQuit() {
        System.exit(0);
    }

    public void processTextAreaLanguageOnKeyRelease() {
        String from = comboLanguage1.getValue().getKey();
        String to = comboLanguage2.getValue().getKey();
        String text = textAreaLanguage1.getText();
        String translatedText = this.interpreterService.translate(from, to, text);
        textAreaLanguage2.setText(translatedText);
        this.setLastKeyPressedTime(null);
    }

    public synchronized void setLastKeyPressedTime(LocalDateTime time) {
        this.lastKeyPressedTime = time;
    }

    void fillLanguages() {
        this.languages = interpreterService.getAvailableLanguages();
        ObservableList<KeyValuePair<String, String>> languagesValuePair = FXCollections.observableArrayList();
        List<KeyValuePair<String, String>> valuePairLanguages = this.languages.stream()
                .map(l -> new KeyValuePair<String, String>(l.getAlfa2Code(), l.getName()))
                .toList();
        languagesValuePair.addAll(valuePairLanguages);

        comboLanguage1.getItems().clear();
        comboLanguage2.getItems().clear();
        comboLanguage1.getItems().addAll(valuePairLanguages);
        comboLanguage2.getItems().addAll(valuePairLanguages);
    }

    private Optional<Language> findLanguageByAlfa2Key(String alfa2Key) {
        return this.languages.stream()
                .filter(language -> language.getAlfa2Code().equals(alfa2Key))
                .findFirst();
    }

    /**
     * init controller components
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //converters
        this.comboLanguage1.setConverter(new StringKeyValuePairConverter(this));
        this.comboLanguage2.setConverter(new StringKeyValuePairConverter(this));
        //links (wrap component in complex component to add them more functionality)
        autoComboLanguage1 = new AutoCompleteComboBoxListener<>(comboLanguage1);
        autoComboLanguage2 = new AutoCompleteComboBoxListener<>(comboLanguage2);
        //set interpreterService
        //interpreterService = InterpreterServiceFactory.getInterpreterService(TranslatorConfig.DEFAULT_API.getKey());
        interpreterService = InterpreterServiceFactory.getInterpreterService(userConfig.getDefaultApi());
        TranslatorConfig.AVAILABLE_APIS.stream()
                .filter(api -> api.getKey().equals(userConfig.getDefaultApi()))
                .findFirst()
                .ifPresent(api -> comboTranslatorApi.setValue(api));
        //fillLanguages
        fillLanguages();

        //translator apis
        comboTranslatorApi.getItems().clear();
        comboTranslatorApi.getItems().addAll(TranslatorConfig.AVAILABLE_APIS);

        //set default languages
        findLanguageByAlfa2Key(userConfig.getDefaultSourceLanguage())
                .ifPresent(lang -> comboLanguage1.setValue(new KeyValuePair<>(lang.getAlfa2Code(), lang.getName())));
        findLanguageByAlfa2Key(userConfig.getDefaultTargetLanguage())
                .ifPresent(lang -> comboLanguage2.setValue(new KeyValuePair<>(lang.getAlfa2Code(), lang.getName())));

        //run threads
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(new DelayTextRunnable(this), 0, DelayTextRunnable.MILLISECONDS_LOOP, TimeUnit.MILLISECONDS);
        //delayTextThread = new DelayTextThread(this);
        //delayTextThread.start();
    }
}

