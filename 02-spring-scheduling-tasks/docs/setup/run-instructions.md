# Running the Application

## Run using Maven

From the project root:

```bash
./mvnw spring-boot:run
```

> Use `mvn spring-boot:run` if you prefer the installed Maven instead of the wrapper.

## Run the packaged JAR

First, build the project:

```bash
./mvnw clean package
```

Then run the JAR file (replace the name if your artifact is different):

```bash
java -jar target/scheduling-tasks-0.0.1-SNAPSHOT.jar
```

## Accessing the application

This example does not expose any HTTP endpoints. When the application is running, the
scheduled task logs the current time every 5 seconds.

You should see log lines similar to:

```text
INFO  ... The time is now 12:34:56
INFO  ... The time is now 12:35:01
INFO  ... The time is now 12:35:06
```

Watch the console output to confirm that the scheduled job is running correctly.


## Example console output

<img width="1226" height="832" alt="Screenshot 2025-12-10 at 2 02 45 PM" src="https://github.com/user-attachments/assets/d05d588c-9524-43c2-a495-39963705b4e8" />


