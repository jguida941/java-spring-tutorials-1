# REST Controller - Greeting Example

```java
@RestController
public class GreetingController {

    public static final String TEMPLATE = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), TEMPLATE.formatted(name));
    }
}
```

## 1. What happens when you hit `/greeting`

You open a browser or use curl and call:

- `GET /greeting`
- or `GET /greeting?name=Justin`

Spring does this:

1. Sees that `/greeting` with method GET matches `@GetMapping("/greeting")`
2. Calls the `greeting(...)` method
3. Takes whatever `Greeting` object the method returns
4. Turns that object into JSON
5. Sends that JSON back to the client

That is all this controller is doing.

## 2. `@RestController`

- Tells Spring: this class is a web controller
- Also tells Spring: every method in here returns data, not HTML
- So whatever you return is written directly into the HTTP response, usually as
  JSON

If this were a normal MVC `@Controller`, you would return a view name like
`"index"` and get HTML. Here, you return a `Greeting` object and get JSON.

## 3. `@GetMapping("/greeting")`

- This says: if someone sends an HTTP GET request to `/greeting`, run this
  method
- `GET` is the HTTP verb
- `/greeting` is the path

So:

- `GET /greeting` → runs `greeting(...)`
- `GET /somethingElse` → this method is not used

There are similar annotations:

- `@PostMapping("/greeting")` for POST
- `@PutMapping`, `@DeleteMapping`, etc.

Internally they are all special cases of `@RequestMapping`, but you can ignore
that for now.

## 4. `@RequestParam(defaultValue = "World") String name`

Look at the method:

```java
public Greeting greeting(@RequestParam(defaultValue = "World") String name)
```

- `@RequestParam` means: look in the query string for a parameter called `name`
- `GET /greeting?name=Justin` → `name` is `"Justin"`
- `defaultValue = "World"` means: if you call `GET /greeting` with no `name`,
  then `name` becomes `"World"` automatically

So:

- `/greeting` → `name = "World"`
- `/greeting?name=Justin` → `name = "Justin"`

## 5. What the method body does

```java
return new Greeting(counter.incrementAndGet(), template.formatted(name));
```

Step by step:

1. `counter.incrementAndGet()`
   - `counter` is an `AtomicLong` that starts at 0
   - `incrementAndGet()` increases it by 1 and returns the new value
   - So first call returns 1, next is 2, then 3, and so on
2. `TEMPLATE.formatted(name)` with `TEMPLATE = "Hello, %s!"`
   - If `name = "World"` → `"Hello, World!"`
   - If `name = "Justin"` → `"Hello, Justin!"`
3. It creates a new `Greeting` object: something like:

```java
new Greeting(1, "Hello, World!");
```

That `Greeting` object is what the method returns.

## 6. How it becomes JSON

You never call JSON code yourself. Spring handles that.

- Because this is `@RestController`, the return value is written into the HTTP
  response body
- Spring looks at the return type, sees it is a plain Java object (`Greeting`)
- Spring uses an HTTP message converter
- Since Jackson is on the classpath, it picks Jackson
- Jackson converts the `Greeting` object to JSON automatically

So if `Greeting` looks like:

```java
public class Greeting {
    private long id;
    private String content;

    // constructor, getters
}
```

Then the HTTP response looks like:

```json
{
  "id": 1,
  "content": "Hello, Justin!"
}
```

You do not write any JSON conversion logic yourself.

## 7. Short Summary

When the client sends `GET /greeting` or `GET /greeting?name=...`, Spring
routes the request to the `greeting` method because of
`@GetMapping("/greeting")`. The `name` query parameter is read by
`@RequestParam`, and if it is missing the value `"World"` is used. The method
creates a `Greeting` object with a unique id and a message like
`"Hello, Justin!"` and returns it. Because the class is annotated with
`@RestController`, Spring automatically converts that `Greeting` object into
JSON and writes it directly to the HTTP response.