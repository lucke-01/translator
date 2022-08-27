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

    @Override
    public void start(Stage stage) throws IOException {
        
    	Parent root = FXMLLoader.load(getClass().getResource("/gui/mainApp/MainAppGui.fxml"));
		Scene scene = new Scene(root);
        
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/assets/app-icon.png")));
		
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
		launch(args);
    }
}