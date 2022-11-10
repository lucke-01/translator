package com.ricardocreates.translator.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * controller which manage about page
 */
public class AboutGuiController implements Initializable {

    @FXML
    private ImageView aboutImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URI imageUri = null;
        try {
            imageUri = getClass().getResource("/gui/assets/app-icon.png").toURI();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
        try {
            aboutImage.setImage(new Image(new FileInputStream(new File(imageUri))));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
