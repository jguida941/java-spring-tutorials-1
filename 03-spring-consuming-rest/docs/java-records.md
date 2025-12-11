# Java Records (simple explanation)

This project uses Java records to hold data. Records are a simple way to create classes that just hold values.

We have two records:
- `Quote` - the outer wrapper with `type` and `value`
- `Value` - the inner object with `id` and `quote`

---

## What is a record?

A record is a special kind of class that holds data. Java creates the constructor, getters, equals, hashCode, and toString for you.

```java
public record Value(Long id, String quote) { }
```

This one line gives you:
- A constructor: `new Value(1L, "some quote")`
- Getter methods: `value.id()` and `value.quote()`
- `equals()` and `hashCode()` based on all fields
- A nice `toString()` for debugging

**The old way (without records):**

```java
public class Value {
    private final Long id;
    private final String quote;

    public Value(Long id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    public Long getId() { return id; }
    public String getQuote() { return quote; }

    @Override
    public boolean equals(Object o) { ... }

    @Override
    public int hashCode() { ... }

    @Override
    public String toString() { ... }
}
```

Records save you from writing all that boilerplate.

---

## Our records

### Value.java

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public record Value(Long id, String quote) { }
```

Matches this JSON:

```json
{
  "id": 5,
  "quote": "Spring Boot gives your project the essentials."
}
```

### Quote.java

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(String type, Value value) { }
```

Matches this JSON:

```json
{
  "type": "success",
  "value": {
    "id": 5,
    "quote": "Spring Boot gives your project the essentials."
  }
}
```

Notice that `Quote` has a `Value` inside it. This matches the nested JSON structure.

---

## @JsonIgnoreProperties(ignoreUnknown = true)

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(String type, Value value) { }
```

**What this does:**
- If the JSON has extra fields we did not define, ignore them.
- Without this, Jackson would throw an error on unknown fields.

**Example:**

If the API returns:

```json
{
  "type": "success",
  "value": { ... },
  "timestamp": "2024-01-15T10:30:00Z"
}
```

We did not define a `timestamp` field in our record. With `@JsonIgnoreProperties(ignoreUnknown = true)`, Jackson just ignores it and the code still works.

**Why use this?**
- APIs can change and add new fields.
- Your code keeps working even if you don't update the record immediately.
- It makes your code more robust.

---

## How JSON becomes a record

When we call:

```java
restClient.get().uri("/api/random").retrieve().body(Quote.class)
```

Here is what happens:

1. `RestClient` sends a GET request to the URL.
2. The response is JSON text.
3. Jackson (a JSON library) reads the JSON.
4. Jackson sees we want a `Quote` object.
5. Jackson matches JSON field names to record field names:
   - `"type"` → `Quote.type`
   - `"value"` → `Quote.value` (which is a `Value` record)
   - `"id"` → `Value.id`
   - `"quote"` → `Value.quote`
6. Jackson creates the objects: `new Value(5, "...")`, then `new Quote("success", value)`.
7. We get back a `Quote` object we can use in Java.

**The key rule:**
Your record field names must match the JSON key names exactly.

---

## Using records

Once you have a `Quote` object, you can access the data:

```java
Quote quote = restClient.get().uri("/api/random").retrieve().body(Quote.class);

// Access fields using getter methods (note: no "get" prefix)
String type = quote.type();       // "success"
Value value = quote.value();      // the nested Value object
Long id = value.id();             // 5
String text = value.quote();      // "Spring Boot gives..."
```

---

## Records are immutable

Records cannot be changed after creation. There are no setter methods.

```java
Value v = new Value(1L, "hello");
// v.setId(2L);  // ERROR - no such method
// v.id = 2L;    // ERROR - field is final
```

**Why immutable?**
- Safer: No accidental changes.
- Thread-safe: Multiple threads can read without locks.
- Predictable: The object always has the same values.

If you need different values, create a new record:

```java
Value v1 = new Value(1L, "hello");
Value v2 = new Value(2L, "world");  // new object, different values
```
