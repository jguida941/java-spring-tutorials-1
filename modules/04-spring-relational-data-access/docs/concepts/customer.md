# Customer Domain Model

{One-paragraph summary: What is this class? Why does it exist? What data does it hold?}

## Overview

{Explain what a domain model is. Why do we need a class to represent customer data?}

## How It Works

{Explain how Java records work - the basics}

```java
// The Customer record definition will go here
```

### Key Points

- **Java Record**: {What does using `record` give you automatically?}
- **Immutability**: {Why are records immutable? Why is that good?}
- **Custom toString()**: {Why override toString when records give you one for free?}

## In This Module

{How Customer is used - created from DB rows, logged to console}

### Mapping to Database

| Record Field | DB Column | Type |
|--------------|-----------|------|
| `id` | `id` | `long` |
| `firstName` | `first_name` | `String` |
| `lastName` | `last_name` | `String` |

{Note: field names use camelCase, DB columns use snake_case - why?}

### RowMapper Lambda

```java
// The lambda that converts ResultSet rows to Customer objects will go here
```

{Explain how this lambda works line by line}

## Common Patterns

### Record with Custom toString()

```java
// Pattern example - record with override
```

{When would you override toString vs use the default?}

### Record vs Class

{When to use a record vs a regular class}

## Gotchas

- **Field naming**: {camelCase in Java vs snake_case in DB}
- **Null handling**: {What happens if DB returns null?}

## Related Concepts

- [JdbcTemplate](jdbc-template.md) - Uses Customer with RowMapper
- [Java Records](https://docs.oracle.com/en/java/javase/17/language/records.html) - Language feature

## References

- [Java 17 Records](https://docs.oracle.com/en/java/javase/17/language/records.html)
- [Spring Guide: Relational Data Access](https://spring.io/guides/gs/relational-data-access/)