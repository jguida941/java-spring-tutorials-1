# Consuming a RESTful Web Service

A Spring Boot application that consumes the [quote-service](../03-quote-service) REST API.

Based on [Spring Guide: Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest).

## How It Works

This consumer exposes a `/quote` endpoint that fetches a random quote from the quote-service:

```
You → GET /quote → consumer (localhost:8081) → GET /api/random → quote-service (localhost:8080)
                                             ← JSON ←
    ← JSON ←
```

## Run

**Step 1:** Start the quote-service first (in a separate terminal):

```bash
cd ../03-quote-service
./mvnw spring-boot:run
```

**Step 2:** Start this consumer:

```bash
./mvnw spring-boot:run
```

**Step 3:** Fetch a quote:

```bash
curl http://localhost:8081/quote
```

## JSON Response

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

| Service        | Port |
|----------------|------|
| quote-service  | 8080 |
| consuming-rest | 8081 |

(Port 8081 is set in this module's `application.properties` via `server.port=8081`.)

## Key Concepts

- **RestClient** – Spring Boot 3.2+ HTTP client for making REST calls.
- **Java Records** – Immutable data classes (`Quote`, `Value`).
- **Constructor Injection** – How Spring provides dependencies.
- **@JsonIgnoreProperties** – Ignore unknown JSON fields during deserialization.

## Documentation

| File                                                 | Explains                                            |
|------------------------------------------------------|-----------------------------------------------------|
| [docs/quote-controller.md](docs/quote-controller.md) | How RestClient and the controller work              |
| [docs/java-records.md](docs/java-records.md)         | What records are and how JSON mapping works         |
| [docs/run-instructions.md](docs/run-instructions.md) | Detailed run steps (expanded version of this section) |

## ADRs

| ADR                                                    | Decision                                           |
|--------------------------------------------------------|----------------------------------------------------|
| [ADR-0003](docs/adr/ADR-0003-use-restclient.md)        | Why RestClient instead of RestTemplate             |
| [ADR-0004](docs/adr/ADR-0004-expose-quote-endpoint.md) | Why a REST endpoint instead of ApplicationRunner   |

## Related

- [03-quote-service](../03-quote-service) – The backend this consumer calls
- [Spring Guide: Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest)
- [Spring Docs: REST Clients](https://docs.spring.io/spring-boot/reference/io/rest-client.html)