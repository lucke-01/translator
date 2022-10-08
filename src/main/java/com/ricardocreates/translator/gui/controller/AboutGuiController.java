package com.ricardocreates.translator.gui.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AboutGuiController implements Initializable {
    
    @FXML
    private ImageView aboutImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URI imageUri = null;
        try {
            imageUri = getClass().getResource("/gui/assets/app-icon.png").toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            aboutImage.setImage(new Image(new FileInputStream(new File(imageUri))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
