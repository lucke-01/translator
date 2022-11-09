package com.ricardocreates.translator.gui;

import com.ricardocreates.translator.gui.controller.AboutGuiController;
import com.ricardocreates.translator.util.FileUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * should be executed with these vm parameters:
 * --add-reads graph.designer.drawing.pane=java.desktop
 * --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED
 * --add-opens graph.designer.drawing.pane/fr.leward.graphdesigner.ui.drawingpane=ALL-UNNAMED
 */
@ExtendWith(ApplicationExtension.class)
class AboutControllerIntegrationTest {
    AboutGuiController aboutGuiController;
    Pane mainRoot;
    Stage mainStage;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage stage) throws IOException {
        //set config file to use src/test/resources/configExample.properties
        System.setProperty("configFile", FileUtil.getFileFromResourcePath("configExample.properties").toString());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainApp/aboutGui.fxml"));
        mainRoot = loader.load();
        Scene scene = new Scene(mainRoot);

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/assets/app-icon.png")));

        stage.setScene(scene);
        stage.show();

        this.aboutGuiController = loader.getController();

        mainStage = stage;
    }

    @Test
    void should_initialize_aboutGuiController() {
        System.out.println(aboutGuiController);
        assertThat(aboutGuiController, is(notNullValue()));
    }

}
