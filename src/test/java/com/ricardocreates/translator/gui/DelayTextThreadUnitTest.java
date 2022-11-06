package com.ricardocreates.translator.gui;

import com.ricardocreates.translator.gui.controller.DelayTextThread;
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
public class DelayTextThreadUnitTest {
    @Mock
    private MainAppGuiController mainAppGuiController;

    @InjectMocks
    private DelayTextThread delayTextThread;

    @Test
    @DisplayName("should process test area")
    void should_process_testArea() {
        //given
        final LocalDateTime nowTime = LocalDateTime.of(2022, 1, 1, 10, 10, 10, 100);
        final LocalDateTime lastKeyPressedTime = LocalDateTime.of(2022, 1, 1, 10, 10, 10, 300);

        given(mainAppGuiController.getLastKeyPressedTime())
                .willReturn(lastKeyPressedTime);
        delayTextThread.setWatchMode(false);
        //when
        delayTextThread.run();
        //then
        verify(mainAppGuiController, times(1)).getLastKeyPressedTime();
        verify(mainAppGuiController, times(1)).processTextAreaLanguageOnKeyRelease();
    }
}
