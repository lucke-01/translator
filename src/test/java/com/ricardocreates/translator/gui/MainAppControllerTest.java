package com.ricardocreates.translator.gui;

import com.ricardocreates.translator.gui.controller.MainAppGuiController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

/**
 * should be executed with these vm parameters:
 * --add-reads graph.designer.drawing.pane=java.desktop
 * --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED
 * --add-opens graph.designer.drawing.pane/fr.leward.graphdesigner.ui.drawingpane=ALL-UNNAMED
 */
@ExtendWith(ApplicationExtension.class)
class MainAppControllerTest {

    MainAppGuiController mainController;
    Pane mainRoot;
    Stage mainStage;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainApp/MainAppGui.fxml"));
        //mainRoot = FXMLLoader.load(getClass().getResource("/gui/mainApp/MainAppGui.fxml"));
        mainRoot = loader.load();
        Scene scene = new Scene(mainRoot);

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/assets/app-icon.png")));

        stage.setScene(scene);
        stage.show();

        this.mainController = loader.getController();

        mainStage = stage;
    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    void should_contain_button_with_text(FxRobot robot) {
        System.out.println(mainController);
        System.out.println(mainController.getTextAreaLanguage1());
        //TextArea textAreaLanguage1 = (TextArea) mainRoot.lookup("#textAreaLanguage1");
        //FxAssert.verifyThat(textAreaLanguage1.getText(), is(notNullValue()));
    }

}
