package com.ricardocreates.translator.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    /**
     * app's main
     *
     * @param args - command parameters
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * init java fx app
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws IOException if something goes wrong
     */
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/mainApp/MainAppGui.fxml"));
            Scene scene = new Scene(root);

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/assets/app-icon.png")));

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * executed when java fx is stopped
     *
     * @throws Exception if something goes wrong
     */
    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        System.exit(0);
    }
}