package com.ricardocreates.translator.gui;

import com.ricardocreates.translator.gui.controller.MainAppGuiController;
import com.ricardocreates.translator.model.KeyValuePair;
import com.ricardocreates.translator.util.FileUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * should be executed with these vm parameters:
 * --add-reads graph.designer.drawing.pane=java.desktop
 * --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED
 * --add-opens graph.designer.drawing.pane/fr.leward.graphdesigner.ui.drawingpane=ALL-UNNAMED
 */
@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class MainAppControllerIntegrationTest extends ApplicationTest {
    private static final String EN_LANGUAGE_KEY = "en";
    private static final String ES_LANGUAGE_KEY = "es";
    MainAppGuiController mainController;
    Pane mainRoot;
    Stage mainStage;
    FXMLLoader loader;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    @Override
    public void start(Stage stage) throws IOException {
        //set config file to use src/test/resources/configExample.properties
        System.setProperty("configFile", FileUtil.getFileFromResourcePath("configExample.properties").toString());

        loader = new FXMLLoader(getClass().getResource("/gui/mainApp/MainAppGui.fxml"));
        mainRoot = loader.load();
        Scene scene = new Scene(mainRoot);

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/assets/app-icon.png")));

        stage.setScene(scene);
        stage.show();

        this.mainController = loader.getController();

        mainStage = stage;
    }

    @Test
    void should_buttonRevertLanguage_changeLanguage(FxRobot robot) {
        //given
        KeyValuePair<String, String> comboLanguage1 = (KeyValuePair<String, String>) robot.lookup("#comboLanguage1").queryAs(ComboBox.class).getValue();
        KeyValuePair<String, String> comboLanguage2 = (KeyValuePair<String, String>) robot.lookup("#comboLanguage2").queryAs(ComboBox.class).getValue();
        assertThat(comboLanguage1.getKey(), is(equalTo(EN_LANGUAGE_KEY)));
        assertThat(comboLanguage2.getKey(), is(equalTo(ES_LANGUAGE_KEY)));
        //when
        clickOn("#buttonRevertLanguage");
        WaitForAsyncUtils.waitForFxEvents();
        //then
        comboLanguage1 = (KeyValuePair<String, String>) robot.lookup("#comboLanguage1").queryAs(ComboBox.class).getValue();
        comboLanguage2 = (KeyValuePair<String, String>) robot.lookup("#comboLanguage2").queryAs(ComboBox.class).getValue();
        assertThat(comboLanguage1.getKey(), is(equalTo(ES_LANGUAGE_KEY)));
        assertThat(comboLanguage2.getKey(), is(equalTo(EN_LANGUAGE_KEY)));
    }

    @Test
    void should_processTextAreaLanguageOnKeyRelease() {
        interact(() -> {
            //given
            this.mainController.getTextAreaLanguage1().setText("hello");
            //when
            mainController.processTextAreaLanguageOnKeyRelease();
            //then
            assertThat(
                    this.mainController.getTextAreaLanguage2().getText().length(),
                    greaterThan(2)
            );
        });
    }


    @Test
    void should_menuPaste_textArea_do_paste() {
        interact(() -> {
            //given
            MenuItem menuItem = (MenuItem) loader.getNamespace().get("menuPaste");
            //when
            menuItem.fire();
        });
    }

    @Test
    void should_menuCut_textArea_do_cut() {
        interact(() -> {
            //given
            MenuItem menuItem = (MenuItem) loader.getNamespace().get("menuCut");
            //when
            menuItem.fire();
            //then
            //Mockito.verify(mainController, times(1)).editMenuCut(any());
        });
    }

    @Test
    void should_menuCopy_textArea_do_copy() {
        interact(() -> {
            //given
            MenuItem menuItem = (MenuItem) loader.getNamespace().get("menuCopy");
            //when
            menuItem.fire();
            //then
            //Mockito.verify(mainController, times(1)).editMenuCopy(any());
        });
    }

    @Test
    void should_menuUndo_textArea_do_undo() {
        interact(() -> {
            //given
            MenuItem menuItem = (MenuItem) loader.getNamespace().get("menuUndo");
            //when
            menuItem.fire();
            //then
            //Mockito.verify(mainController, times(1)).editMenuUndo();
        });
    }

    @Test
    void should_menuRedo_textArea_do_redo() {
        interact(() -> {
            //given
            MenuItem menuItem = (MenuItem) loader.getNamespace().get("menuRedo");
            //when
            menuItem.fire();
            //then
            //Mockito.verify(mainController, times(1)).editMenuRedo();
        });
    }

    @Test
    void should_menuSelectAll_textArea_do_selectAll() {
        interact(() -> {
            //given
            MenuItem menuItem = (MenuItem) loader.getNamespace().get("menuSelectAll");
            //when
            menuItem.fire();
            //mainController.editMenuSelectAll();
            //then
            //Mockito.verify(mainController, times(1)).editMenuSelectAll();
        });
    }

    @Test
    void should_menuUnselectAll_textArea_do_unselectAll() {
        interact(() -> {
            //given
            MenuItem menuItem = (MenuItem) loader.getNamespace().get("menuUnselectAll");
            //when
            menuItem.fire();
            //then
            //Mockito.verify(mainController, times(1)).editMenuUnselectAll();
        });
    }

    @Test
    void should_open_about() {
        interact(() -> {
            assertDoesNotThrow(() -> {
                MenuItem menuHelpAbout = (MenuItem) loader.getNamespace().get("menuHelpAbout");
                menuHelpAbout.fire();
            });
        });
    }

}
