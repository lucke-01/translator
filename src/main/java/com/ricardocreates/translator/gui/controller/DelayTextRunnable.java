package com.ricardocreates.translator.gui.controller;

import javafx.application.Platform;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * thread which wait sometimes since the last key pressed in a input
 * and then will call to process that input
 */
@NoArgsConstructor
public class DelayTextRunnable implements Runnable {
    /**
     * controller to communicate
     */
    private MainAppGuiController mainAppGuiController;
    @Setter
    private long desiredDurationBetweenLastKeyPressed = 700L;

    public DelayTextRunnable(MainAppGuiController mainAppGuiController) {
        this.mainAppGuiController = mainAppGuiController;
    }

    @Override
    public void run() {
        LocalDateTime lastKeyPressedTime = mainAppGuiController.getLastKeyPressedTime();
        if (lastKeyPressedTime != null) {
            final LocalDateTime now = LocalDateTime.now();
            final Duration durationBetweenLastKeyPressed = Duration.between(lastKeyPressedTime, now);
            if (durationBetweenLastKeyPressed.toMillis() > desiredDurationBetweenLastKeyPressed) {
                Platform.runLater(() -> {
                    mainAppGuiController.processTextAreaLanguageOnKeyRelease();
                });
            }
        }
    }
}
