package com.ricardocreates.translator.gui;

import com.ricardocreates.translator.gui.controller.DelayTextRunnable;
import com.ricardocreates.translator.gui.controller.MainAppGuiController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DelayTextRunnableUnitTest {
    @Mock
    private MainAppGuiController mainAppGuiController;

    @InjectMocks
    private DelayTextRunnable delayTextThread;

    @Test
    @DisplayName("should process test area")
    void should_process_testArea() {
        //given
        final LocalDateTime lastKeyPressedTime = LocalDateTime.of(2022, 1, 1, 10, 10, 10, 300);

        given(mainAppGuiController.getLastKeyPressedTime())
                .willReturn(lastKeyPressedTime);
        //when
        delayTextThread.run();
        //then
        verify(mainAppGuiController, times(1)).getLastKeyPressedTime();
        verify(mainAppGuiController, times(1)).processTextAreaLanguageOnKeyRelease();
    }

    @Test
    @DisplayName("should not process test area")
    void should_not_process_testArea() {
        //given
        final LocalDateTime lastKeyPressedTime = LocalDateTime.of(9999, 1, 1, 10, 10, 10, 300);

        given(mainAppGuiController.getLastKeyPressedTime())
                .willReturn(lastKeyPressedTime);
        //when
        delayTextThread.run();
        //then
        verify(mainAppGuiController, times(1)).getLastKeyPressedTime();
        verify(mainAppGuiController, times(0)).processTextAreaLanguageOnKeyRelease();
    }
}