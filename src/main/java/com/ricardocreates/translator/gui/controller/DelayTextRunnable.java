package com.ricardocreates.translator.gui.controller;

import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * thread which wait sometimes since the last key pressed in a input
 * and then will call to process that input
 */
@NoArgsConstructor
public class DelayTextRunnable implements Runnable {
    /**
     * time to wait
     */
    public static final long MILLISECONDS_LOOP = 100;
    /**
     * desired time to wait until the last key press
     */
    private static final long DESIRED_DURATION_BETWEEN_LAST_KEY_PRESSED = 700;
    /**
     * controller to communicate
     */
    private MainAppGuiController mainAppGuiController;

    public DelayTextRunnable(MainAppGuiController mainAppGuiController) {
        this.mainAppGuiController = mainAppGuiController;
    }

    @Override
    public void run() {
        LocalDateTime lastKeyPressedTime = mainAppGuiController.getLastKeyPressedTime();
        if (lastKeyPressedTime != null) {
            final LocalDateTime now = LocalDateTime.now();
            final Duration durationBetweenLastKeyPressed = Duration.between(lastKeyPressedTime, now);
            if (durationBetweenLastKeyPressed.toMillis() > DESIRED_DURATION_BETWEEN_LAST_KEY_PRESSED) {
                mainAppGuiController.processTextAreaLanguageOnKeyRelease();
            }
        }
    }
}
