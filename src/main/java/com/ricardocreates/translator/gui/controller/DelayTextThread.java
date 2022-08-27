package com.ricardocreates.translator.gui.controller;

import java.time.Duration;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DelayTextThread extends Thread {
    private static final long MILISECONDS_LOOP = 100;
    private MainAppGuiController mainAppGuiController;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(MILISECONDS_LOOP);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (mainAppGuiController.getLastKeyPressedTime() != null) {
                final Duration durationBetweenLastKeyPressedd = Duration.between(mainAppGuiController.getLastKeyPressedTime(), LocalDateTime.now());
                if (durationBetweenLastKeyPressedd.toMillis() >  700) {
                    mainAppGuiController.processTextAreaLanguageOnKeyRelease();
                }
            }
        }
    }
}
