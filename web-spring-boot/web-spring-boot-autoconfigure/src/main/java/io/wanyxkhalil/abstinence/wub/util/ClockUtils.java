package io.wanyxkhalil.abstinence.wub.util;

import java.time.Clock;

public class ClockUtils {

    private static final Clock DEFAULT_CLOCK = Clock.systemDefaultZone();

    /**
     * 获取当前瞬时时间
     */
    public static long mills() {
        return DEFAULT_CLOCK.millis();
    }
}
