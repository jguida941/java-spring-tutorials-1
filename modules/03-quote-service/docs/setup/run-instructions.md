# Run Instructions

## Start the Quote Service

```bash
./mvnw spring-boot:run
```

The service starts on port 8080 (default).

## Test the Endpoints

### Get all quotes

```bash
curl http://localhost:8080/api/
```

### Get a random quote

```bash
curl http://localhost:8080/api/random
```

### Get a specific quote by ID

```bash
curl http://localhost:8080/api/5
```

## Expected Response

```json
{
  "type": "success",
  "value": {
    "id": 3,
    "quote": "Spring Boot is the best thing that has happened to Java development in a long time."
  }
}
```

## Port Configuration

Default port is 8080. To change:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=9000
```

## Running with the Consumer

When running both quote-service and consuming-rest together:

1. Start this service first (port 8080)
2. Start consuming-rest second (port 8081)
3. Call `http://localhost:8081/quote` to fetch through the consumer
