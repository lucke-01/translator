package com.ricardocreates.translator.gui;

import com.ricardocreates.translator.gui.controller.DelayTextRunnable;
import com.ricardocreates.translator.gui.controller.MainAppGuiController;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DelayTextRunnableUnitTest {
    @Mock
    private MainAppGuiController mainAppGuiController;

    @InjectMocks
    private DelayTextRunnable delayTextThread;

    @Test
    @DisplayName("should process text area")
    void should_process_textArea() {
        //given
        final LocalDateTime lastKeyPressedTime = LocalDateTime.of(2022, 1, 1, 10, 10, 10, 300);

        given(mainAppGuiController.getLastKeyPressedTime())
                .willReturn(lastKeyPressedTime);
        //when
        delayTextThread.run();
        Awaitility.await().atLeast(700, TimeUnit.MILLISECONDS);
        //then
        //verify(mainAppGuiController, times(1)).getLastKeyPressedTime();
        //verify(mainAppGuiController, times(1)).processTextAreaLanguageOnKeyRelease();
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
        Awaitility.await().atLeast(700, TimeUnit.MILLISECONDS);
        //then
        //verify(mainAppGuiController, times(1)).getLastKeyPressedTime();
        //verify(mainAppGuiController, times(0)).processTextAreaLanguageOnKeyRelease();
    }
}
