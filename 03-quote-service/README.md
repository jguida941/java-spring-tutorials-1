# Quote Service

A simple Spring Boot REST API that serves quotes. This service is the backend for the [03-consuming-rest](../03-spring-consuming-rest) tutorial.

## File Index

```
03-quote-service/
├── pom.xml
├── src/main/java/com/example/quoteservice/
│   ├── QuoteServiceApplication.java    # Main entry point
│   ├── QuoteController.java            # REST endpoints
│   ├── Quote.java                      # Record for JSON response
│   └── Value.java                      # Record for nested JSON object
├── src/main/resources/
│   └── application.properties
└── docs/
    ├── DEVELOPER_NOTES.md               # My developer notes
    ├── images/                          # Screenshots
    ├── setup/
    │   └── spring-initializr.md         # Project setup from start.spring.io
    ├── concepts/
    │   └── quote-controller.md          # Explains the controller and Java Streams
    └── adr/
        ├── ADR-0001-split-provider-consumer.md  # Why separate modules
        └── ADR-0002-rest-api-shape.md           # API design decisions
```

## Endpoints

| Method | Path         | Description       |
|--------|--------------|-------------------|
| GET    | `/api/`      | All quotes        |
| GET    | `/api/random`| Random quote      |
| GET    | `/api/{id}`  | Quote by ID (1-10)|

## JSON Response

```json
{
  "type": "success",
  "value": {
    "id": 1,
    "quote": "Working with Spring Boot is like pair-programming with the Spring developers."
  }
}
```

## Terminal output from `curl http://localhost:8080/api/random`

<img width="658" height="214" alt="Screenshot 2025-12-11 at 12 45 55 AM" src="docs/images/quote-service-output.png" />

## Run

```bash
./mvnw spring-boot:run
```

The service starts on `http://localhost:8080`.

## Test the Endpoints

```bash
curl http://localhost:8080/api/random
curl http://localhost:8080/api/
curl http://localhost:8080/api/1
```

## Related

- [03-consuming-rest](../03-spring-consuming-rest) - REST client that consumes this service
- [Spring Guide: Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest)
- [Spring Guide: Building a RESTful Web Service](https://spring.io/guides/gs/rest-service) - background on REST controllers
