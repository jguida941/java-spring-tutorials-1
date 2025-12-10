# Scheduling Tasks with Spring

> **Source:** [Spring Guide - Scheduling Tasks](https://spring.io/guides/gs/scheduling-tasks)

This guide walks you through the steps for scheduling tasks with Spring.

## What You Will Build

You will build an application that prints out the current time every five seconds by using Spring Framework's `@Scheduled` annotation.

## What You Need

- About 15 minutes
- A favorite text editor or IDE
- Java 17 or later

## How to Complete This Guide

Like most Spring Getting Started guides you can start from scratch and complete each step, or you can jump straight to the solution, by viewing the code in this repository.

To see the end result in your local environment, you can do one of the following:

- Download and unzip the source repository for this guide
- Clone the repository using Git: `git clone https://github.com/spring-guides/gs-scheduling-tasks.git`
- Fork the repository which lets you request changes to this guide through submission of a pull request

## Starting with Spring Initializr

You can use this pre-initialized project and click Generate to download a ZIP file. This project is configured to fit the examples in this guide.

To manually initialize the project:

1. Navigate to [https://start.spring.io](https://start.spring.io). This service pulls in all the dependencies you need for an application and does most of the setup for you.
2. Choose either Gradle or Maven and the language you want to use. This guide assumes that you chose Java and Gradle.
3. Click Generate.
4. Download the resulting ZIP file, which is an archive of an application that is configured with your choices.

> If your IDE has the Spring Initializr integration, you can complete this process from your IDE.

## Enable Scheduling

Although scheduled tasks can be embedded in web applications, the simpler approach (shown in this guide) creates a standalone application. To do so, package everything in a single, executable JAR file, driven by a Java `main()` method.

The following snippet (from `src/main/java/com/example/schedulingtasks/SchedulingTasksApplication.java`) shows the application class:

```java
@SpringBootApplication
@EnableScheduling
public class SchedulingTasksApplication {
```

Spring Initializr adds the `@SpringBootApplication` annotation to our main class. `@SpringBootApplication` is a convenience annotation that adds all of the following:

- **@Configuration**: Tags the class as a source of bean definitions for the application context.
- **@EnableAutoConfiguration**: Spring Boot attempts to automatically configure your Spring application based on the dependencies that you have added.
- **@ComponentScan**: Tells Spring to look for other components, configurations, and services. If specific packages are not defined, recursive scanning begins with the package of the class that declares the annotation.

Additionally, add the `@EnableScheduling` annotation. This annotation enables Spring's scheduled task execution capability.

## Create a Scheduled Task

Create a new class `src/main/java/com/example/schedulingtasks/ScheduledTasks.java`:

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

The `Scheduled` annotation defines when a particular method runs.

This example uses `fixedRate()`, which specifies the interval between method invocations, measured from the start time of each invocation. Other options are `cron()` and `fixedDelay()`. For periodic tasks, exactly one of these three options must be specified, and optionally, `initialDelay()`. For a one-time task, it is sufficient to just specify an `initialDelay()`.

## Running the Application

You should now be able to run the application by executing the main method in `SchedulingTasksApplication`. You can run the program from your IDE, or by executing the following Gradle command in the project root directory:

```bash
./gradlew bootRun
```

Doing so starts the application, and the method annotated with `@Scheduled` runs. You should see log messages similar to:

```
20yy-mm-ddT07:23:01.665-04:00  INFO 19633 --- [   scheduling-1] c.e.schedulingtasks.ScheduledTasks       : The time is now 07:23:01
20yy-mm-ddT07:23:06.663-04:00  INFO 19633 --- [   scheduling-1] c.e.schedulingtasks.ScheduledTasks       : The time is now 07:23:06
20yy-mm-ddT07:23:11.663-04:00  INFO 19633 --- [   scheduling-1] c.e.schedulingtasks.ScheduledTasks       : The time is now 07:23:11
```

This example uses `fixedRate()` scheduling, so the application runs indefinitely until you interrupt it manually.

## Testing with the awaitility Dependency

To properly test your application, you can use the awaitility library. Since Spring Boot 3.2, this is a dependency that Boot manages.

You can create a new test or view the existing test at `src/test/java/com/example/schedulingtasks/ScheduledTasksTest.java`:

```java
@SpringBootTest
public class ScheduledTasksTest {

    @SpyBean
    ScheduledTasks tasks;

    @Test
    public void reportCurrentTime() {
        await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
            verify(tasks, atLeast(2)).reportCurrentTime();
        });
    }
}
```

This test automatically runs when you run the `./gradlew clean build` task.

## Building the Application

This section describes different ways to run this guide:

- Building and executing a JAR file
- Building and executing a Docker container using Cloud Native Buildpacks
- Building and executing a native image
- Building and executing a native image container using Cloud Native Buildpacks

Regardless of how you choose to run the application, the output should be the same.

### JAR File

To run the application, you can package the application as an executable jar:

```bash
./gradlew clean build
```

You can then run the jar with:

```bash
java -jar build/libs/gs-scheduling-tasks-0.0.1-SNAPSHOT.jar
```

### Docker with Cloud Native Buildpacks

Alternatively, if you have a Docker environment available, you could create a Docker image directly from your Maven or Gradle plugin, using buildpacks. With Cloud Native Buildpacks, you can create Docker compatible images that you can run anywhere.

Spring Boot includes buildpack support directly for both Maven and Gradle. This means you can type a single command and quickly get a sensible image into a locally running Docker daemon.

To create a Docker image using Cloud Native Buildpacks:

```bash
./gradlew bootBuildImage
```

With a Docker environment enabled, you can run the application with:

```bash
docker run docker.io/library/gs-scheduling-tasks:0.0.1-SNAPSHOT
```

## Native Image Support

Spring Boot also supports compilation to a native image, provided you have a GraalVM distribution on your machine.

To create a native image with Gradle using Native Build Tools, first make sure that your Gradle build contains a plugins block that includes `org.graalvm.buildtools.native`:

```groovy
plugins {
    id 'org.graalvm.buildtools.native' version '0.9.28'
    ...
}
```

You can then run:

```bash
./gradlew nativeCompile
```

When the build completes, you will be able to run the code with a near-instantaneous start up time by executing:

```bash
build/native/nativeCompile/gs-scheduling-tasks
```

### Native Image with Buildpacks

You can also create a Native Image using Buildpacks:

```bash
./gradlew bootBuildImage
```

Once the build completes, you can start your application with:

```bash
docker run docker.io/library/gs-scheduling-tasks:0.0.1-SNAPSHOT
```

## Summary

Congratulations! You created an application with a scheduled task.