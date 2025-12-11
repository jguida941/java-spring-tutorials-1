# Quote Service

A simple Spring Boot REST API that serves quotes. This service is the backend for the [03-consuming-rest](../03-spring-consuming-rest) tutorial.

## Endpoints

| Method | Path         | Description       |
|--------|--------------|-------------------|
| GET    | `/api/`      | All quotes        |
| GET    | `/api/random`| Random quote      |
| GET    | `/api/{id}`  | Quote by ID (1–10)|

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

- [03-consuming-rest](../03-spring-consuming-rest) – REST client that consumes this service
- [Spring Guide: Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest)
- [Spring Guide: Building a RESTful Web Service](https://spring.io/guides/gs/rest-service) – background on REST controllers