package com.ricardocreates.translator.gui.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * thread which wait sometimes since the last key pressed in a input
 * and then will call to process that input
 */
@NoArgsConstructor
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
    /**
     * if true will be looping until thread is stopped
     * if false only one execution
     */
    @Setter
    private boolean watchMode = true;
    /**
     * indicates if it should execute the main code of the thread
     */
    private boolean run = true;

    public DelayTextThread(MainAppGuiController mainAppGuiController) {
        this.mainAppGuiController = mainAppGuiController;
    }

    @Override
    public void run() {
        while (run) {
            try {
                //TODO: change this for: https://stackoverflow.com/a/54394101/6207773
                Thread.sleep(MILLISECONDS_LOOP);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            LocalDateTime lastKeyPressedTime = mainAppGuiController.getLastKeyPressedTime();
            if (lastKeyPressedTime != null) {
                final LocalDateTime now = LocalDateTime.now();
                final Duration durationBetweenLastKeyPressed = Duration.between(lastKeyPressedTime, now);
                if (durationBetweenLastKeyPressed.toMillis() > DESIRED_DURATION_BETWEEN_LAST_KEY_PRESSED) {
                    mainAppGuiController.processTextAreaLanguageOnKeyRelease();
                }
            }
            if (!watchMode) {
                this.run = false;
            }
        }
    }
}
