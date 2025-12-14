# Starting with Spring Initializr

<!--
  TEMPLATE: Spring Initializr Setup

  Instructions:
  1. Replace {ARTIFACT_NAME} with your artifact (e.g., relational-data-access)
  2. Replace {DEPENDENCIES} with required dependencies (e.g., Spring Data JDBC, H2 Database)
  3. Update the screenshot path to your module's image
  4. Adjust manual steps as needed for your guide
-->

You can use this pre-initialized project and click Generate to download a ZIP file. This project is configured to fit the examples in this tutorial.

## Project Settings

| Setting | Value |
|---------|-------|
| Project | Maven |
| Language | Java |
| Spring Boot | 4.0.0 |
| Group | com.example |
| Artifact | {ARTIFACT_NAME} |
| Java | 17 |
| Dependencies | {DEPENDENCIES} |

## Manual Setup

To manually initialize the project:

1. Navigate to https://start.spring.io. This service pulls in all the dependencies you need for an application and does most of the setup for you.
2. Choose Maven and Java.
3. Select Spring Boot 4.0.0 and Java 17.
4. Type "{ARTIFACT_NAME}" in the "Artifact" form field.
5. Click Dependencies and select {DEPENDENCIES}.
6. Click Generate.
7. Download the resulting ZIP file, which is an archive of a web application that is configured with your choices.

If your IDE has the Spring Initializr integration, you can complete this process from your IDE.

You can also fork the project from Github and open it in your IDE or other editor.

## Project Setup Screenshot

<!-- Add a screenshot of your Spring Initializr configuration -->
<img width="1190" height="882" alt="Spring Initializr Configuration" src="../images/spring-initializr.png" />
