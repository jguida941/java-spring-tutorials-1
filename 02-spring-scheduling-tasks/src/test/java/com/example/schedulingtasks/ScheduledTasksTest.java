package com.example.schedulingtasks;

import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

/**
 * Integration test that checks whether the scheduled tasks actually run.
 *
 * <p>It starts the full Spring Boot context, wraps {@link ScheduledTasks}
 * in a Mockito spy, then waits up to 10 seconds and verifies that
 * {@link ScheduledTasks#reportCurrentTime()} is called at least twice.</p>
 */
@SpringBootTest
class ScheduledTasksTest {

    @MockitoSpyBean
    private ScheduledTasks tasks;

    /**
     * Verifies that {@code reportCurrentTime()} is triggered repeatedly
     * by the scheduler (at least two calls within 10 seconds).
     */
    @Test
    public void reportCurrentTime() {
        await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
            verify(tasks, atLeast(2)).reportCurrentTime();
        });
    }
}