# ScheduledTasks

This class defines a simple scheduled job that prints the current time to the log every 5 seconds.

```java
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
```

## `@Component`

- Marks this class as a Spring bean.
- Spring will create an instance of `ScheduledTasks` and manage it in the application context.
- Needed so Spring can find and run the scheduled method.

## Logger field

```java
private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
```

- Creates a logger for this class.
- `log.info(...)` writes an informational log message.
- Logging is preferred over `System.out.println` in Spring apps.

## Date format field

```java
private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
```

- Defines how the time will be printed.
- `"HH:mm:ss"` means hours:minutes:seconds in 24-hour time.
- Example output: `13:45:07`

## `@Scheduled(fixedRate = 5000)`

```java
@Scheduled(fixedRate = 5000)
public void reportCurrentTime() { ... }
```

- Tells Spring to run this method on a schedule.
- `fixedRate = 5000` means run every 5000 milliseconds (every 5 seconds).
- Spring uses the scheduler enabled by `@EnableScheduling` in the main app class.

## Method body

```java
log.info("The time is now {}", dateFormat.format(new Date()));
```

- `new Date()` gets the current date and time.
- `dateFormat.format(...)` turns it into a string like `"13:45:07"`.
- `"The time is now {}"` is a message template.
- `{}` is replaced by the formatted time.
- The final log line looks like: `The time is now 13:45:07`

---

## Why SLF4J?

> In short: both versions can log, but SLF4J is the one Spring Boot is built around,
> so it needs less setup and works better with `application.properties`.

- SLF4J is a logging facade (a common logging API) used by Spring Boot.
- Your code logs through SLF4J, and Spring Boot plugs in a real logger behind it
  (Logback by default).
- This is why the guide uses:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
```



## Version A vs Version B

For comparison:

**Version A - java.util.logging (JUL):**

```java
import java.util.logging.Logger;

private static final Logger logger = Logger.getLogger(ScheduledTasks.class.getName());
```

**Version B - SLF4J (Spring guide):**

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
```

Both create a logger and can print messages, but Version B integrates with Spring Boot's
logging system.



## What Spring Boot gives you "for free" with SLF4J (Version B)

When you use SLF4J with Spring Boot, you can control logging with simple properties,
no extra Java code.

### 1. Change Log Levels

```properties
# application.properties
logging.level.root=INFO
logging.level.com.example.schedulingtasks=DEBUG
```

### 2. Change Log Format

```properties
# application.properties
logging.pattern.console=%d{HH:mm:ss} %-5level [%thread] %logger{36} - %msg%n
```

### 3. Use Parameterized Logging

```java
log.info("The time is now {}", time);
```

All of this works automatically because you use:

```java
org.slf4j.Logger + LoggerFactory.getLogger(...)
```

Spring Boot wires SLF4J to Logback for you.



## If You Use java.util.logging Directly (Version A)

If you use `java.util.logging.Logger` instead of SLF4J:

```java
import java.util.logging.Logger;

private static final Logger logger = Logger.getLogger(ScheduledTasks.class.getName());
```

Then:

1. **Spring Boot's `logging.*` properties do not control your logger in the same clean way.**
   You would need your own `logging.properties`:

   ```properties
   # logging.properties for java.util.logging
   .level=INFO
   com.example.schedulingtasks.level=FINE
   handlers=java.util.logging.ConsoleHandler
   java.util.logging.ConsoleHandler.formatter=java.util.logging.SimpleFormatter
   ```

   And start the JVM with:

   ```bash
   java -Djava.util.logging.config.file=logging.properties ...
   ```

   Or add Java code to configure handlers and formatters.

2. **You lose SLF4J features Spring expects:**
   - No `{}` placeholders, you must write:
     ```java
     logger.info("The time is now " + time);
     ```
   - No easy way to swap logging backends.

3. **If you want java.util.logging to flow through SLF4J, you add an extra bridge dependency:**

   ```xml
   <!-- pom.xml -->
   <dependency>
       <groupId>org.slf4j</groupId>
       <artifactId>jul-to-slf4j</artifactId>
   </dependency>
   ```

   And sometimes code like:

   ```java
   import org.slf4j.bridge.SLF4JBridgeHandler;

   static {
       SLF4JBridgeHandler.removeHandlersForRootLogger();
       SLF4JBridgeHandler.install();
   }
   ```

   This is already more work than just using SLF4J from the start.


## Takeaway

- **Version A (java.util.logging)** will still log, but you have to do more manual
  configuration.
- **Version B (SLF4J)** plugs straight into Spring Boot's logging system, so you control
  it with simple `application.properties` and get consistent behavior with the rest of
  the framework.
- This is exactly why people read the documentation and examples: there is no way to
  "guess" that Spring Boot expects SLF4J without looking at the guides or reference docs.