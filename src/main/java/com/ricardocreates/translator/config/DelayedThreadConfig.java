package com.ricardocreates.translator.config;

import lombok.Builder;
import lombok.Data;

/**
 * Model of configuration file
 */
@Data
@Builder
public class DelayedThreadConfig {
    /**
     * desired time to wait until the last key press
     */
    private long desiredDurationBetweenLastKeyPressed = 0L;
    /**
     * time to wait
     */
    private long millisecondsLoop = 0L;
}
