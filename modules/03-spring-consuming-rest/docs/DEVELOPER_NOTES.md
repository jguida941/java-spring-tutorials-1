# Developer Notes

**Developer:** Justin Guida  
**Date:** December 11, 2025  
**Status:** Accepted

This file explains, in my own words, the main pieces of this project:
the `Quote` / `Value` JSON model, the `QuoteController` REST client,
and the Spring Boot application setup (entry point, config, tests).

## Index

| File                                                                                                                  | Role                                           | Notes                          |
|-----------------------------------------------------------------------------------------------------------------------|------------------------------------------------|--------------------------------|
| [`Quote.java`](../src/main/java/com/example/consumingrest/Quote.java)                                                 | Record for JSON response                       | Shared JSON shape with service |
| [`Value.java`](../src/main/java/com/example/consumingrest/Value.java)                                                 | Nested record (`id`, `quote` text)             | Shared JSON shape with service |
| [`QuoteController.java`](../src/main/java/com/example/consumingrest/QuoteController.java)                             | REST client controller, fetches quote from API | Uses `RestClient`, error handling |
| [`ConsumingRestApplication.java`](../src/main/java/com/example/consumingrest/ConsumingRestApplication.java)           | Main Spring Boot application entry point       | Boots the app on port 8081     |
| [`application.properties`](../src/main/resources/application.properties)                                              | Configuration                                  | Port + quote-service base URL  |
| [`ConsumingRestApplicationTests.java`](../src/test/java/com/example/consumingrest/ConsumingRestApplicationTests.java) | Tests                                          | Spring Boot test scaffold      |
| [`QuoteControllerTest.java`](../src/test/java/com/example/consumingrest/QuoteControllerTest.java)                     | Endpoint tests with mocked backend             | MockRestServiceServer          |

---

## Quote.java and JSON mapping patterns

### [Quote.java](../src/main/java/com/example/consumingrest/Quote.java)
Shared JSON shape between quote-service and consuming client.

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(String type, Value value) { }
```

`@JsonIgnoreProperties(ignoreUnknown = true)` tells Jackson to ignore any
JSON properties that do not have a corresponding field in the Quote record.
This is useful if the JSON response contains extra data that I do not care
about.

This file defines a `Quote` record. Using a record lets me represent the
response with less boilerplate than a normal Java class.

**`Quote` has two fields:**
- `type` - a `String` representing the type of response
  (for example, `"success"`)
- `value` - a `Value` object that holds the `id` and the actual quote text

Spring, through Jackson, uses this record to map the JSON response from
the quote-service into Java, and then back into JSON when my `/quote`
endpoint returns it.

```json
{
  "type": "success",
  "value" : { "id": 1, "quote": "..."}
}
```

Records replace all that boilerplate - no manual constructors, getters,
`toString()`, `equals()`, or `hashCode()`. One line does it all.

There are many ways you can code this, for example using `final` vs
non-final fields, or using getters/setters vs direct field access in
simple examples. This is why using records is nice - it reduces the
complexity of defining data-holding types, and it simply works with
Jackson for JSON mapping.

### Example: All-args constructor (manual style)

This is how you can write a class by hand - pass all data up
front with `new Quote("success", value)`.

```java
public class Quote {

  private String type;
  private Value value;

  // All-args constructor
  public Quote(String type, Value value) {
    this.type = type;
    this.value = value;
  }

  // Getters
  public String getType() { return type; }
  public Value getValue() { return value; }

  // Optional setters (only if you want mutability)
  public void setType(String type) { this.type = type; }
  public void setValue(Value value) { this.value = value; }
}
```

Unlike the no-arg + setters pattern that Jackson uses by default
(`new Quote()` then `setType(...)` / `setValue(...)`), this style
expects all data up front in the constructor call.

### Example: No-arg constructor + setters (Jackson default)

This is the pattern Jackson uses by default for JSON deserialization.
Jackson calls the no-arg constructor, then uses setters to fill fields.

```java
public class Quote {

  private String type;
  private Value value;

  // No-arg constructor (explicit or omitted; both are fine)
  public Quote() { }

  // Getters
  public String getType() { return type; }
  public Value getValue() { return value; }

  // Setters - Jackson calls these after construction
  public void setType(String type) { this.type = type; }
  public void setValue(Value value) { this.value = value; }
}
```

## Step by step explanation of mutable fields with setters

### Step 1: What exists in the class?

| Component       | What it does                   | Code                                             |
|-----------------|--------------------------------|--------------------------------------------------|
| **Fields**      | Data storage inside the object | `private String type;`<br>`private Value value;` |
| **Constructor** | How the object is created      | `public Quote() { }`                             |
| **Setters**     | How we change the fields       | `setType(...)`, `setValue(...)`                  |
| **Getters**     | How we read the fields         | `getType()`, `getValue()`                        |

**Summary:**

| Component       | Purpose                            |
|-----------------|------------------------------------|
| **Fields**      | Data storage inside the object     |
| **Constructor** | How the object is created          |
| **Setters**     | How we change the fields           |
| **Getters**     | How we read the fields             |

### Step 2: When are the fields "constructed"?

**When you call this (e.g., inside
[QuoteController.java](../src/main/java/com/example/consumingrest/QuoteController.java)):**

```java
Quote q = new Quote();
```

**This happens:**
1. JVM allocates a new Quote object.
2. The fields `type` and `value` are created inside that object and get
   default values:
    - `type` = null
    - `value` = null
3. The constructor `public Quote()` runs (it does nothing extra here).

**After `new Quote()`:**
- The object exists.
- The fields exist, but they are still null.

**Key Point:** Nothing has called `setType` or `setValue` yet.

### Step 3: When are the fields "set"?

**When you call this (e.g., inside
[QuoteController.java](../src/main/java/com/example/consumingrest/QuoteController.java)):**

```java
q.setType("success");
q.setValue(new Value(1L, "some quote"));
```

**This happens:**

| Method               | What runs             | Result                                          |
|----------------------|-----------------------|-------------------------------------------------|
| `setType("success")` | `this.type = type;`   | `type` changes from `null` to `"success"`       |
| `setValue(...)`      | `this.value = value;` | `value` changes from `null` to `Value` instance |

**Summary:**
- The fields are created at construction (`new Quote()`).
- They are filled/changed when setters are called.

### Step 4: How does Jackson use this?

**When Jackson deserializes JSON into Quote, it does:**

1. `Quote q = new Quote();` (calls the no-arg constructor)
2. `q.setType(jsonTypeValue);`
3. `q.setValue(jsonValueObject);`

**Key Point:** Same exact steps as above, just done automatically by Jackson.


### Step 5: Why can these fields not be final?

**What happens to the field `type`:**

| When                            | What happens                          |
|---------------------------------|---------------------------------------|
| At construction (`new Quote()`) | `type` is created with default `null` |
| Later, in `setType(...)`        | `type` is assigned a real value       |

That means the field changes after the object is constructed.

**If you wrote:**

```java
private final String type;
private final Value value;
```

**Java rules say:**
- A final field must be assigned exactly once, in a constructor or at
  declaration.
- You cannot assign to it later in a setter.

**So with final:**
- `public Quote() { }` is illegal unless you set both `type` and `value`
  inside it.
- `setType` and `setValue` would not be allowed to do `this.type = ...`
  or `this.value = ...` because that would change a final field.

**This is why:**

> "This pattern needs non-final fields because we change them after
> construction."

**We change them here:**

```java
public void setType(String type) {
  this.type = type;  // this is a change after construction
}
```

If the field was `final`, this line would not be allowed.

### Final Summary

| What           | When                                     |
|----------------|------------------------------------------|
| Object created | `new Quote()` runs                       |
| Fields exist   | At creation, but are `null`              |
| Fields filled  | When setters are called                  |
| Why not final  | Setters change fields after construction |


## Final vs non-final fields for JSON mapping

There are two common ways to map JSON into a `Quote` class:

1. Non-final fields with a no-arg constructor and setters (mutable)
2. Final fields with a constructor only (immutable), which is what records give you


### 1. Non-final fields + no-arg constructor + setters (mutable)

(This is the same pattern as "Example: No-arg constructor + setters
(Jackson default)" above. Repeated here to compare with the final-field
version.)

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String type;
    private Value value;

    // 1) Jackson calls this no-arg constructor.
    // In Java, if you do not write any constructor, the compiler
    // will create this empty no-arg constructor for you. I am
    // writing it explicitly here to show the "new Quote()" step.
    public Quote() { }

    // 2) Then Jackson calls these setters to fill the fields
    public void setType(String type) {
        this.type = type;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    // Getters so we can read the values
    public String getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }
}
```

**What happens:**
1. Jackson does `new Quote()` (fields exist but are null).
2. Jackson calls `setType(jsonType)` and `setValue(jsonValue)` to fill
   the fields.

**Key Point:**
Fields cannot be final here, because setters need to change them after
construction.


### 2. Final fields + constructor only (immutable, no setters)

```java
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private final String type;
    private final Value value;

    // Jackson uses THIS constructor directly
    @JsonCreator
    public Quote(
            @JsonProperty("type") String type,
            @JsonProperty("value") Value value) {
        this.type = type;
        this.value = value;
    }

    // Only getters, no setters (object is immutable)
    public String getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }
}
```

**What happens:**
1. Jackson sees `@JsonCreator` on the constructor.
2. It reads JSON fields `"type"` and `"value"`.
3. It calls `new Quote(typeFromJson, valueFromJson)`.
4. The fields are set once in the constructor and never change, so they can
   be final.

**Key Point:**
Here we do not use setters at all. All data comes in through the
constructor once.


### 3. How this relates to record Quote(String type, Value value)

A Java record is basically the second pattern, but the compiler writes
the constructor and getters for me.

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(String type, Value value) { }
```

The compiler generates something very close to:
```java
public final class Quote {
    private final String type;
    private final Value value;

    public Quote(String type, Value value) {
        this.type = type;
        this.value = value;
    }

    public String type() { return type; }
    public Value value() { return value; }

    // plus equals, hashCode, toString...
}
```

**Summary of the three patterns:**

| Pattern                     | Description                                          |
|-----------------------------|------------------------------------------------------|
| **Non-final + setters**     | Easy, mutable, uses no-arg constructor + setters     |
| **Final + constructor**     | Immutable, fields set once in the constructor        |
| **Record**                  | Final + constructor pattern with less boilerplate    |

This is why using a record `Quote(String type, Value value)` is a clean fit
for this project. You get the immutable "final field + constructor" style
without writing all the boilerplate by hand.

---

## Remaining files (high level)


### [Value.java](../src/main/java/com/example/consumingrest/Value.java)
Shared JSON shape between quote-service and consuming client.

Represents the nested "value" part of the JSON: an `id` and the `quote`
text itself.

```java
public record Value(Long id, String quote) { }
```

---

### [QuoteController.java](../src/main/java/com/example/consumingrest/QuoteController.java)

REST controller that consumes the quote-service API and exposes `/quote` endpoint.

**Key annotations:**
- `@RestController` - makes this a REST endpoint that returns JSON
- `@GetMapping("/quote")` - maps GET requests to the handler method
- `@Value("${quote.service.base-url}")` - injects property from application.properties

**RestClient usage (Spring Boot 3.2+):**
```java
this.restClient = builder.baseUrl(baseUrl).build();

// In the handler method:
return restClient
    .get().uri("/api/random")
    .retrieve()
    .body(Quote.class);
```

The fluent API:
1. `.get()` - HTTP GET request
2. `.uri("/api/random")` - appended to base URL
3. `.retrieve()` - executes the request
4. `.body(Quote.class)` - deserializes JSON to Quote record

**Error handling:**
```java
try {
    return restClient.get()...;
} catch (RestClientException e) {
    log.error("Failed to fetch quote", e);
    return new Quote("error", new Value(-1L, "Quote service unavailable"));
}
```
Returns a fallback response instead of crashing when quote-service is down.
See [ADR-0005](adr/ADR-0005-error-handling-fallback.md) for rationale.

---

### [ConsumingRestApplication.java](../src/main/java/com/example/consumingrest/ConsumingRestApplication.java)

Standard Spring Boot entry point. Nothing special here.

```java
@SpringBootApplication
public class ConsumingRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }
}
```

**What `@SpringBootApplication` does:**
- `@Configuration` - marks class as bean definition source
- `@EnableAutoConfiguration` - enables Spring Boot auto-config (including RestClient.Builder bean)
- `@ComponentScan` - scans current package for `@Component`, `@RestController`, etc.

---

### [application.properties](../src/main/resources/application.properties)

Configuration for this module.

```properties
server.port=8081
quote.service.base-url=http://localhost:8080
```

| Property | Purpose |
|----------|---------|
| `server.port=8081` | Avoids conflict with quote-service (which uses 8080) |
| `quote.service.base-url` | Externalized backend URL for RestClient |

**Why externalize the URL?**
- Easy to change without code modifications
- Different values for dev/test/prod environments
- Can override via command line: `-Dquote.service.base-url=http://prod:8080`
- See [ADR-0006](adr/ADR-0006-externalize-base-url.md) for rationale

---

### [QuoteControllerTest.java](../src/test/java/com/example/consumingrest/QuoteControllerTest.java)

Tests the QuoteController using `@SpringBootTest` with `@AutoConfigureMockMvc` and `@AutoConfigureMockRestServiceServer`.

```java
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class QuoteControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired MockRestServiceServer server;
}
```

**Key testing patterns:**
- `MockRestServiceServer` - mocks HTTP responses without real network calls
- `server.expect()` - sets up expected requests and canned responses
- `MockMvc` - tests HTTP endpoints without starting a real server
- Tests both happy path (backend returns quote) and error path (backend unavailable)
