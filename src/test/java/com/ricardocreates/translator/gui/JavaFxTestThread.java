package com.ricardocreates.translator.gui;

import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;

import java.io.IOException;

/**
 * to check component programmatically without using controller we need create a java fx thread
 */
@AllArgsConstructor
public class JavaFxTestThread extends Thread {
    /**
     * Code to test inside of java fx thread
     */
    private final Runnable testingCode;

    @Override
    public void run() {
        Platform.runLater(() -> {
            try {
                new App().start(new Stage()); // Create and
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // testing
            testingCode.run();
            Thread.currentThread().interrupt();
        });
    }
}
