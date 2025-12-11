# Knowledge Summary

## [Quote.java](../src/main/java/com/example/consumingrest/Quote.java)
```Java
@JsonIgnoreProperties(ignoreUnknown = true)
```
This tells Jackson to ignore any JSON properties that do not have a
corresponding field in the Quote record. This is useful if the JSON
response contains extra data that I do not care about.

This file defines a `Quote` record. Using a record lets me represent
the response with less boilerplate than a normal Java class.


`Quote` has two fields:
- `type` – a `String` representing the type of response
  (for example, `"success"`)
- `value` – a `Value` object that holds the `id` and the actual quote text

Spring, through Jackson, uses this record to map the JSON response from
the quote-service into Java, and then back into JSON when my `/quote`
endpoint returns it.

```JSON
{
  "type": "success",
  "value" : { "id": 1, "quote": "..."}
}
```
Without the use of records, I would have to write a full Java class with
constructors, getters, `toString()`, `equals()`, and `hashCode()` methods, etc.

```Java
public class Quote {
    private nal string type;
    
}

        

```