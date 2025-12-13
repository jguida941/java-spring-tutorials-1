package com.example.schedulingtasks;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Simple scheduled task that logs the current time every 5 seconds.
 *
 * <p>Spring creates this bean and runs the {@link #reportCurrentTime()} method
 * on a fixed schedule because of the {@code @Scheduled} annotation.</p>
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    // DateTimeFormatter is thread-safe (unlike SimpleDateFormat)
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", LocalTime.now().format(TIME_FORMATTER));
    }
}
