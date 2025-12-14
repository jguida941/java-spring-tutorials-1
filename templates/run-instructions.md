# Run Instructions

<!--
  TEMPLATE: Run Instructions

  Instructions:
  1. Replace {ARTIFACT_NAME} with your artifact (e.g., relational-data-access)
  2. Replace {PORT} with the server port (check Module Ports table in AGENTS.md)
  3. Replace {ENDPOINT} with primary endpoint (e.g., /customers, /quotes)
  4. Update example output to match your module's response
  5. Add any prerequisites (e.g., start another service first)
-->

## Prerequisites

<!-- Uncomment if this module depends on another service
Before running this module, ensure the following is running:
- **{DEPENDENCY_MODULE}** on port {DEPENDENCY_PORT}
-->

## Running the Application

If you use Maven, you can run the application by using:

```bash
./mvnw spring-boot:run
```

Alternatively, you can build the JAR file with:

```bash
./mvnw clean package
```

And then run the JAR file:

```bash
java -jar target/{ARTIFACT_NAME}-0.0.1-SNAPSHOT.jar
```

The application will start on port {PORT}.

## Testing the Endpoint

Once the application is running, you can test it by navigating to:

```
http://localhost:{PORT}/{ENDPOINT}
```

You should see a response similar to:

```json
{
  "example": "response"
}
```

<!-- Add more examples specific to your module's endpoints -->
