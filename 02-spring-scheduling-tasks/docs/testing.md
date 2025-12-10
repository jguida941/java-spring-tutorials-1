# Testing Scheduled Tasks

This document explains how to test scheduled tasks using Spring Boot Test and Awaitility.

This test assumes `ScheduledTasks` is a Spring `@Component` with a method annotated
with `@Scheduled(fixedRate = 5000)`.

```java
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
```

> **Note:** This uses `@MockitoSpyBean` instead of `@SpyBean` because with Spring Boot 4.0.0
> the older `@SpyBean` used in some Spring Boot 3.x examples has been replaced by the
> new Mockito-based annotations such as `@MockitoSpyBean`.



## `@SpringBootTest`

- Tells JUnit to start the full Spring Boot application context for this test.
- It is like running your app, but inside a test.
- Beans such as `ScheduledTasks` are created just like in the real application.
- This is an integration test, not a tiny unit test.



## `@MockitoSpyBean`

```java
@MockitoSpyBean
private ScheduledTasks tasks;
```

- Replaces the normal `ScheduledTasks` bean in the Spring context with a spy (from Mockito).
- A spy is a real object that still runs the real code, but you can also:
   - Record how many times methods were called.
   - Verify interactions.
- In this test, it lets you check that `reportCurrentTime()` was actually called by the scheduler.
- **Spring Boot 4.0.0:** Uses `@MockitoSpyBean` instead of the deprecated `@SpyBean`.



## `@Test`

```java
@Test
public void reportCurrentTime() { ... }
```

- Standard JUnit 5 test method.
- JUnit runs this method as a test case.



## Awaitility: `await().atMost(...).untilAsserted(...)`

```java
await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
    verify(tasks, atLeast(2)).reportCurrentTime();
});
```

### Step by step:

1. **`await()`** - Starts an Awaitility wait. Awaitility is a library for waiting until
   something becomes true.

2. **`atMost(Durations.TEN_SECONDS)`** - Give the scheduler up to 10 seconds to do its work.
   If the condition is not met within 10 seconds, the test fails.

3. **`untilAsserted(() -> { ... })`** - Repeatedly runs the assertion inside the lambda
   until it passes or time runs out.

4. **Inside the lambda:**

   ```java
   verify(tasks, atLeast(2)).reportCurrentTime();
   ```

   Uses Mockito's `verify`:
   - `tasks` is the spy
   - `atLeast(2)` means "called two or more times"
   - `reportCurrentTime()` is the method you expect to be called
  

## Running tests

```bash
./mvnw test
```

## Running tests

From the project root (`02-spring-scheduling-tasks/`):

```bash
./mvnw test
```

## Test output

Example `./mvnw test` run with all tests passing:

<img width="1658" height="937" alt="Screenshot 2025-12-10 at 3 04 09 PM" src="https://github.com/user-attachments/assets/c4182b3b-172a-434a-8977-2244fb700084" />


## Summary

What the test does:

1. Start the Spring Boot app in a test context.
2. Use a spy on `ScheduledTasks` so we can see how often `reportCurrentTime()` is called.
3. Wait up to 10 seconds.
4. During that time, check repeatedly that `reportCurrentTime()` has been called at least twice.
5. If the scheduler is working (`fixedRate = 5000` ms), the method is called at least twice
   within 10 seconds and the test passes.
