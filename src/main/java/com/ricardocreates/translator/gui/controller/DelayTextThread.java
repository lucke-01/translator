package com.ricardocreates.translator.gui.controller;

import lombok.AllArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * thread which wait some times since the last key pressed in a input
 * and then will call to process that input
 */
@AllArgsConstructor
public class DelayTextThread extends Thread {
    /**
     * time to wait
     */
    private static final long MILLISECONDS_LOOP = 100;
    /**
     * desired time to wait until the last key press
     */
    private static final long DESIRED_DURATION_BETWEEN_LAST_KEY_PRESSED = 700;
    /**
     * controller to communicate
     */
    private MainAppGuiController mainAppGuiController;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(MILLISECONDS_LOOP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (mainAppGuiController.getLastKeyPressedTime() != null) {
                final Duration durationBetweenLastKeyPressed = Duration.between(mainAppGuiController.getLastKeyPressedTime(), LocalDateTime.now());
                if (durationBetweenLastKeyPressed.toMillis() > DESIRED_DURATION_BETWEEN_LAST_KEY_PRESSED) {
                    mainAppGuiController.processTextAreaLanguageOnKeyRelease();
                }
            }
        }
    }
}
