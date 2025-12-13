# Starting with Spring Initializr

You can use this pre-initialized project and click Generate to download a ZIP file. This project is configured to fit the examples in this tutorial.

To manually initialize the project:

1. Navigate to https://start.spring.io. This service pulls in all the dependencies you need for an application and does most of the setup for you.
2. Choose either Gradle or Maven and the language you want to use.
3. Type `quote-service` in the **Artifact** form field.
4. Click **Dependencies** and select **Spring Web**.
5. Click **Generate**.
6. Download the resulting ZIP file, which is an archive of a web application that is configured with your choices.

If your IDE has Spring Initializr integration, you can complete this process from your IDE.

You can also fork the project from GitHub and open it in your IDE or other editor.

## Project Settings

| Setting      | Value                    |
|-------------|--------------------------|
| Project     | Maven                    |
| Language    | Java                     |
| Spring Boot | 4.0.0                    |
| Group       | `com.example`           |
| Artifact    | `quote-service`         |
| Name        | `quote-service`         |
| Package     | `com.example.quoteservice` |
| Packaging   | Jar                      |
| Java        | 17                       |

## Dependencies

| Dependency | Description                                                                                         |
|------------|-----------------------------------------------------------------------------------------------------|
| Spring Web | Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the embedded container. |
