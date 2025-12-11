# Run Instructions

## Prerequisites

The quote-service must be running on port 8080 before starting this consumer.

## Step 1: Start the Quote Service

In a separate terminal:

```bash
cd ../03-quote-service
./mvnw spring-boot:run
```

Verify it's running:

```bash
curl http://localhost:8080/api/random
```

## Step 2: Start the Consumer

```bash
./mvnw spring-boot:run
```

The consumer starts on port 8081 (configured in `application.properties`).

## Step 3: Fetch a Quote

```bash
curl http://localhost:8081/quote
```

You should see a JSON response like:

```json
{
  "type": "success",
  "value": {
    "id": 3,
    "quote": "Spring Boot is the best thing that has happened to Java development in a long time."
  }
}
```

## Ports

| Service          | Port |
|------------------|------|
| quote-service    | 8080 |
| consuming-rest   | 8081 |

## Flow

```
curl :8081/quote ’ consumer ’ :8080/api/random ’ provider ’ JSON ’ consumer ’ JSON back
```
